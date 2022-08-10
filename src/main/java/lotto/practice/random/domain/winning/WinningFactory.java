package lotto.practice.random.domain.winning;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.winning.command.WinningResult;
import lotto.practice.random.domain.winning.entity.WinningInfo;

import java.util.List;
import java.util.Map;

@Slf4j
public class WinningFactory {

    public static WinningInfo createWinningInfo(WinningResult winner, Map<RankType, Long> winningAmount, List<Integer> totalCount) {
        //몇등인지 알아야함
        return WinningInfo.builder()
                .totSellingPrice(winner.getTotSellingPrice())
                .user(winner.getUser())
                .lottoCycleNum(winner.getLottoCycleNum())
                .winnerRank(winner.getWinnerRank())
                //.totalAmount(getAmount(winningAmount, winner.getWinnerRank()))
                //.winnerTotalCount(getTotalCount(totalCount, winner.getWinnerRank()))
                .build();
    }

    //TODO: getAmount, getTotalCount Rank 객체로 뺄 것
    public static Long getAmount(Map<RankType, Long> winningAmount, int rank) {
        switch (rank) {
            case 5:
                return winningAmount.get(RankType.FIFTH);
            case 4:
                return winningAmount.get(RankType.FOURTH);
            case 3:
            case 2:
                return winningAmount.get(RankType.SECOND);
            case 1:
                return winningAmount.get(RankType.FIRST);
            default:
                break;
        }
        return null;
    }

    private static int getTotalCount(List<Integer> totalCount, int rank) {
        switch (rank) {
            case 5:
                return totalCount.get(5);
            case 4:
                return totalCount.get(4);
            case 3:
                return totalCount.get(3);
            case 2:
                return totalCount.get(2);
            case 1:
                return totalCount.get(1);
            default:
                break;
        }
        return 0;
    }


}
