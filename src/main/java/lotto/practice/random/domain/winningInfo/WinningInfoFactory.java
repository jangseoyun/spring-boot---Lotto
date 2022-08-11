package lotto.practice.random.domain.winningInfo;

import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.Winner.entity.Winner;
import lotto.practice.random.domain.winningAmount.RankAmountCommand;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;

public class WinningInfoFactory {

    /**
     * - 회차 번호
     * - 당첨번호 6자리
     * - 보너스 번호
     * - 순위
     * - 등위별 총 당첨금액
     * - 등위별 총 당첨자의 수
     * - 당첨자가 수령하게될 금액
     * <p>
     * - 당첨자 아이디 * 로 나오도록 -> user
     * - 수동인지 자동인지 응모 타입 (winningStorage 컬럼에 그럼 응모 타입 있어야함) -> winner로 전달받아서 저장
     */

    public static WinningInfoCommand setWinningInfo(Winner winner, WinningMap winningMap) {
        return WinningInfoCommand.builder()
                .lottoCycleNum(winner.getLottoCycleNum())
                .sixBall(winner.getSixBall())
                .bonusBall(winner.getBonusBall())
                .winnerRank(winner.getWinnerRank())
                .rankTotalAmount(matchAmount(winningMap.getRankAmountCommand(), winner.getWinnerRank()))
                .rankTotalCount(matchCount(winningMap.getTotalCount(), winner.getWinnerRank()))
                .userId(winner.getUser().getUserId())
                .build();
    }

    public static Long matchAmount(RankAmountCommand getAmountCommand, RankType rank) {

        if (rank == RankType.FIFTH) {
            return getAmountCommand.getFifthAmount();
        } else if (rank == RankType.FOURTH) {
            return getAmountCommand.getFourthAmount();
        } else if (rank == RankType.THIRD || rank == RankType.SECOND) {
            return getAmountCommand.getThirdNsecondAmount();
        } else if (rank == RankType.FIRST) {
            return getAmountCommand.getFirstAmount();
        } else {
            return 0L;
        }

    }

    public static int matchCount(TotalCount getTotalCount, RankType rank) {

        if (rank == RankType.FIFTH) {
            return getTotalCount.getFifthTotalCount();
        } else if (rank == RankType.FOURTH) {
            return getTotalCount.getFourthTotalCount();
        } else if (rank == RankType.THIRD) {
            return getTotalCount.getThirdTotalCount();
        } else if (rank == RankType.SECOND) {
            return getTotalCount.getSecondTotalCount();
        } else if (rank == RankType.FIRST) {
            return getTotalCount.getFirstTotalCount();
        } else {
            return 0;
        }

    }


}
