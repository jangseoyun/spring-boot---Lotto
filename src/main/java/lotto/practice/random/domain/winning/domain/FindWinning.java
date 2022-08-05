package lotto.practice.random.domain.winning.domain;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.dto.WinningDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Component
public class FindWinning {

    public List<WinningDto> getWinnerList(LottoApi getThisWeekWinning, List<MachineCycleStorage> findAllUser) {
        List<WinningDto> winnerList = new ArrayList<>();
        //등위별 당첨자
        for (MachineCycleStorage userOne : findAllUser) {
            //1. 당첨 여부 및 당첨자 순위
            int findWinnerRank = findWinnerRank(getThisWeekWinning, userOne);
            WinningDto winner = WinningDto.builder()
                    .sixBall(userOne.getSixBall())
                    .totSellingPrice(getThisWeekWinning.getTotSellamnt())
                    .user(userOne.getUser())
                    .lottoCycleNum(getThisWeekWinning.getDrwNo())
                    .winnerRank(findWinnerRank)
                    .bonusBall(userOne.getBonusBall())
                    .build();
            winnerList.add(winner);
        }

        return winnerList;
    }

    public int findWinnerRank(LottoApi getThisWeekWinning, MachineCycleStorage userOne) {
        //1. 해당 유저의 6개의 추출 번호 확인
        List<Ball> sixNum = getSixnum(userOne);
        //2. 맞는 볼이 있는지 검증
        int resultCheckBall = vaildSixBall(sixNum, getThisWeekWinning);
        //3. 당첨 순위
        if (resultCheckBall >= 3) {
            return calculateCount(resultCheckBall, getThisWeekWinning.getBnusNo(), userOne.getBonusBall());
        }
        return 0;
    }

    //6개의 list<ball>만들기
    public List<Ball> getSixnum(MachineCycleStorage userOne) {
        List<Ball> getSixnum = new ArrayList<>();
        getSixnum.add(userOne.getBall1());
        getSixnum.add(userOne.getBall2());
        getSixnum.add(userOne.getBall3());
        getSixnum.add(userOne.getBall4());
        getSixnum.add(userOne.getBall5());
        getSixnum.add(userOne.getBall6());
        return getSixnum;
    }

    /**
     * 로또 추첨 번호 비교
     *
     * @param sixNum
     * @param getThisWeekWinning
     * @return 맞은 번호 갯수
     */
    private int vaildSixBall(List<Ball> sixNum, LottoApi getThisWeekWinning) {
        List<Ball> result = sixNum.stream()
                .filter(ball -> ball.getValue() == getThisWeekWinning.getDrwtNo1()
                        || ball.getValue() == getThisWeekWinning.getDrwtNo2()
                        || ball.getValue() == getThisWeekWinning.getDrwtNo3()
                        || ball.getValue() == getThisWeekWinning.getDrwtNo4()
                        || ball.getValue() == getThisWeekWinning.getDrwtNo5()
                        || ball.getValue() == getThisWeekWinning.getDrwtNo6())
                .collect(Collectors.toList());
        log.info("checkRank = " + result);
        log.info("checkRank size = " + result.size());

        return result.size();
    }

    /**
     * 보너스 번호 일치 여부
     */
    public boolean checkBonusBall(Ball userBonusNum, int bonusBall) {
        if (userBonusNum.getValue() == bonusBall) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 랭킹 찾아 내는 로직
     * 1~4위
     * 1등 : 6개
     * 2등 : 5개 + 보너스 번호
     * 3등 : 5개
     * 4등 : 4개
     */
    //TODO: 일급 객체로 만들기
    //당첨금 계산하려면 누적되어야함 당첨자 카운트
    int firstTotalCount = 0;
    int secondTotalCount = 0;
    int thirdTotalCount = 0;
    int fourthTotalCount = 0;
    int fifthTotalCount = 0;

    //등위별 총 당첨자 파악
    public int calculateCount(int resultCheckBall, int getBonusNum, Ball userBonusBall) {

        if (resultCheckBall == 3) {
            fifthTotalCount += 1;
            return 5;

        } else if (resultCheckBall == 4) {
            fourthTotalCount += 1;
            return 4;

        } else if (resultCheckBall == 5) {
            //보너스 번호 체크
            boolean checkBonusBall = checkBonusBall(userBonusBall, getBonusNum);
            if (checkBonusBall == true) {
                secondTotalCount += 1;
                return 2;
            }
            thirdTotalCount += 1;
            return 3;

        } else {
            firstTotalCount += 1;
            return 1;

        }
    }

    /**
     * * 등위별 총 당첨금액 계산
     * 1등 :  (총 당첨금 - i ) * 0.75
     * 2등 :  (총 당첨금 - i ) * 0.125
     * 3등 :  (총 당첨금 -  i )* 0.125
     * 4등 : 전체 금액 - (50,000)
     * 5등 : 전체 금액 - (5,000)
     * * 4 + 5등의 합 = i
     */
    public Map<String, Long> calculateAmount(Long totSellingPrice, List<Integer> totalCount) {

        //5위 총 금액
        Long fifthTotalAmount = (totalCount.get(5) * 5000L);
        //4위 총 금액
        Long fourthTotalAmount = (fourthTotalCount * 50000L);
        //4위 5위 합산
        Long fifAndFour = (fifthTotalAmount + fourthTotalAmount);

        //2위,3위
        Long secondAndThirdAmount = ((totSellingPrice - fifAndFour) * (25 / 100)) / 2;

        //1위
        Long firstAmount = ((totSellingPrice - fifAndFour) * (75 / 100));

        Map<String, Long> winnerAmount = new HashMap<>();
        winnerAmount.put("fifthTotalAmount", fifthTotalAmount);
        winnerAmount.put("fourthTotalAmount", fourthTotalAmount);
        winnerAmount.put("secondAndThirdAmount", secondAndThirdAmount);
        winnerAmount.put("firstAmount", firstAmount);

        return winnerAmount;
    }

    //TODO: 일급 객체
    public List<Integer> makeTotalCount() {
        List<Integer> totalCount = new ArrayList<>();
        totalCount.add(firstTotalCount);
        totalCount.add(secondTotalCount);
        totalCount.add(thirdTotalCount);
        totalCount.add(fourthTotalCount);
        totalCount.add(fifthTotalCount);
        return totalCount;
    }
}
