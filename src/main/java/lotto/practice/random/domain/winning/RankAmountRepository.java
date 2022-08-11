package lotto.practice.random.domain.winning;

import lotto.practice.random.domain.Winner.RankType;

public interface RankAmountRepository {

    int getRankTotalCount(RankType rank, Long lottoCycleNum);

    Long getTotalSellAmount(Long lottoCycleNum);


}
