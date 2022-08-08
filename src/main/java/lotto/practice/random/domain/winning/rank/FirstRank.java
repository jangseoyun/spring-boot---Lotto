package lotto.practice.random.domain.winning.rank;

import lotto.practice.random.domain.winning.RankType;

/**
 * 1등 총 당첨자 수
 */
public class FirstRank extends Rank {

    public FirstRank(Long count) {
        super(count);
    }

    @Override
    public RankType getRankType() {
        return RankType.FIRST;
    }

    @Override
    public Long getWinningTotalCount(Long count) {
        return count;
    }

}
