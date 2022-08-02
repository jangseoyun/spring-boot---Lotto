package lotto.practice.random.domain.winning;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.dto.WinningDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FindWinning {

    //TODO : 도메인 옮기기

    /**
     * 랭킹 찾아 내는 로직
     * 1~4위
     * 1등 : 6개
     * 2등 : 5개 + 보너스 번호
     * 3등 : 5개
     * 4등 : 4개
     */
    public WinningInfo findWinner(LottoApi getThisWeekWinning, List<MachineCycleStorage> findAllUser) {
        //등위별 총 당첨자
        for (MachineCycleStorage userOne : findAllUser) {
            //1. 당첨자 찾기 rank
            WinningDto findWinnerRank = findWinnerRank(getThisWeekWinning, userOne);
            //2. 등위별 당첨자 총 카운트 만들기
            iterCount(findAllUser, findWinnerRank);
            //3. 당첨금 계산
            Map<String, Object> winnerAmount = calculateAmount(getThisWeekWinning.getTotSellamnt());

            WinningFactory.createWinningInfo(findWinnerRank, winnerAmount, getThisWeekWinning, userOne);

        }

    }


    public WinningDto findWinnerRank(LottoApi getThisWeekWinning, MachineCycleStorage userOne) {
        //1. 해당 유저의 6개의 추출 번호 확인
        List<Ball> sixNum = getSixnum(userOne);
        //2. 맞는 볼이 있는지 검증 -> 당첨자 순위 확인
        int winnerRank = checkRank(sixNum, getThisWeekWinning);
    }

    //6개의 list<ball>만들기
    public List<Ball> getSixnum(MachineCycleStorage userOne) {
        List<Ball> createUserSixnum = new ArrayList<>();
        createUserSixnum.add(1, userOne.getBall1());
        createUserSixnum.add(2, userOne.getBall2());
        createUserSixnum.add(3, userOne.getBall3());
        createUserSixnum.add(4, userOne.getBall4());
        createUserSixnum.add(5, userOne.getBall5());
        createUserSixnum.add(6, userOne.getBall6());
        return createUserSixnum;
    }

    //당첨순위 확인
    private int checkRank(List<Ball> sixNum, LottoApi getThisWeekWinning) {
        List<Ball> result = sixNum.stream()
                .filter(ball -> sixNum.contains(getThisWeekWinning.getDrwtNo1()))
                .filter(ball -> sixNum.contains(getThisWeekWinning.getDrwtNo2()))
                .filter(ball -> sixNum.contains(getThisWeekWinning.getDrwtNo3()))
                .filter(ball -> sixNum.contains(getThisWeekWinning.getDrwtNo4()))
                .filter(ball -> sixNum.contains(getThisWeekWinning.getDrwtNo5()))
                .filter(ball -> sixNum.contains(getThisWeekWinning.getDrwtNo6()))
                .collect(Collectors.toList());
        log.info("checkRank = " + result);
        log.info("checkRank size = " + result.size());

        return result.size();
    }

    private void iterCount(List<MachineCycleStorage> findAllUser, WinningDto findWinnerRank) {

        //2. 등위별 총 당첨자 계산
        for (int i = 0; i < findAllUser.size(); i++) {
            calculateCount(findWinnerRank.getWinnerRank());
        }
    }

    //TODO: 일급 객체로 만들기
    //당첨금 계산하려면 누적되어야함 당첨자 카운트
    int firstTotalCount = 0;
    int secondTotalCount = 0;
    int thirdTotalCount = 0;
    int fourthTotalCount = 0;
    int fifthTotalCount = 0;

    //등위별 총 당첨자 파악
    public void calculateCount(int getWinnerRank) {

        if (getWinnerRank == 5) {
            fifthTotalCount += 1;
        } else if (getWinnerRank == 4) {
            fourthTotalCount += 1;
        } else if (getWinnerRank == 3) {
            thirdTotalCount += 1;
        } else if (getWinnerRank == 2) {
            secondTotalCount += 1;
        } else if (getWinnerRank == 1) {
            firstTotalCount += 1;
        }
    }

    //등위별 당첨금 계산
    public Map<String, Object> calculateAmount(Long totSellingPrice) {
        //5위 총 금액
        Long fifthTotalAmount = (fifthTotalCount * 5000L);
        Long fourthTotalAmount = (fourthTotalCount * 50000L);
        Long fifAndFour = (fifthTotalAmount + fourthTotalAmount);

        //2위,3위
        double secondAndThirdAmount = ((totSellingPrice - fifAndFour) * 0.125);

        //1위
        double firstAmount = ((totSellingPrice - fifAndFour) * 0.75);

        Map<String, Object> winnerAmount = new HashMap<>();
        winnerAmount.put("fifthTotalAmount", fifthTotalAmount);
        winnerAmount.put("fourthTotalAmount", fourthTotalAmount);
        winnerAmount.put("secondAndThirdAmount", secondAndThirdAmount);
        winnerAmount.put("firstAmount", firstAmount);

        return winnerAmount;
    }
}
