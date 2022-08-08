package lotto.practice.random.domain.winning.rank;

import lotto.practice.random.domain.winning.RankType;

/**
 * 2등 총 당첨자 수
 */
public class SecondRank extends Rank {

    public SecondRank(Long count) {
        super(count);
    }

    @Override
    public RankType getRankType() {
        return RankType.SECOND;
    }

    @Override
    public Long getWinningTotalCount(Long count) {
        return count;
    }
}
