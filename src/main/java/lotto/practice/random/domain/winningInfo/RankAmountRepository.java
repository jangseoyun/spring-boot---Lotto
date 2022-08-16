package lotto.practice.random.domain.winningInfo;

import lotto.practice.random.domain.winner.RankType;

public interface RankAmountRepository {

    Long getRankTotalCount(RankType rank, Long lottoCycleNum);

    Long getTotalSellAmount(Long lottoCycleNum);

}
