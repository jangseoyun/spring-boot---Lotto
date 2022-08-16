package lotto.practice.random.domain.winner.domain;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winner.RankType;
import lotto.practice.random.domain.winner.command.WinnerCommand;
import lotto.practice.random.domain.winner.ranktype.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FindWinnerImpl implements FindWinner {

    @Override
    public List<WinnerCommand> getWinnerList(LottoApi getThisWeekWinning, List<MachineCycleStorage> findAllUser) {

        List<WinnerCommand> winnerList = new ArrayList<>();
        //등위별 당첨자
        for (MachineCycleStorage userOne : findAllUser) {
            //1. 당첨 여부 및 당첨자 순위, 당첨자 등록을 위한 정보
            RankType getWinnerRank = findWinnerRank(getThisWeekWinning, userOne);

            WinnerCommand winner = WinnerCommand.builder()
                    .sixBall(userOne.getSixBall())
                    .bonusBall(userOne.getBonusBall())
                    .user(userOne.getUser())
                    .winnerRank(getWinnerRank)
                    .lottoCycleNum(getThisWeekWinning.getDrwNo())
                    .drwNoDate(getThisWeekWinning.getDrwNoDate())
                    .build();

            winnerList.add(winner);
        }
        return winnerList;
    }

    private RankType findWinnerRank(LottoApi getThisWeekWinning, MachineCycleStorage userOne) {
        //1. 해당 유저의 6개의 추출 번호 확인
        List<Ball> sixNum = getSixnum(userOne);
        //2. 맞는 볼이 있는지 검증(return : 맞은 공 갯수)
        int resultCheckBall = vaildSixBall(sixNum, getThisWeekWinning);
        //3. 당첨 순위 (return : 당첨 등수 Rank)
        if (resultCheckBall >= 3) {
            return calculateCount(resultCheckBall, getThisWeekWinning.getBnusNo(), userOne.getBonusBall());
        }
        return new FailRank().getRankType();
    }

    //6개의 list<ball>만들기
    private List<Ball> getSixnum(MachineCycleStorage userOne) {
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
     * 랭킹 찾아 내는 로직
     * 1~4위
     * 1등 : 6개
     * 2등 : 5개 + 보너스 번호
     * 3등 : 5개
     * 4등 : 4개
     */

    /**
     * 로또 추첨 번호 비교
     *
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
     * 등위별 당첨자 누적 계산
     */
    private RankType calculateCount(int resultCheckBall, int getBonusNum, Ball userBonusBall) {

        if (resultCheckBall == 3) {
            return new FifthRank().getRankType();

        } else if (resultCheckBall == 4) {
            return new FourthRank().getRankType();

        } else if (resultCheckBall == 5) {
            //보너스 번호 체크
            boolean checkBonusBall = checkBonusBall(userBonusBall, getBonusNum);
            if (checkBonusBall == true) {
                return new SecondRank().getRankType();
            }
            return new ThirdRank().getRankType();

        } else {
            return new FirstRank().getRankType();

        }
    }

    /**
     * 보너스 번호 일치 여부
     */
    private boolean checkBonusBall(Ball userBonusNum, int bonusBall) {
        if (userBonusNum.getValue() == bonusBall) {
            return true;
        } else {
            return false;
        }
    }

}
