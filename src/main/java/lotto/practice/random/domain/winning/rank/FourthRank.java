package lotto.practice.random.domain.winning.rank;

import lotto.practice.random.domain.winning.RankType;

/**
 * 4등 총 당첨자 수
 * 4등 총 당첨 금액
 */
public class FourthRank extends Rank {

    public FourthRank(Long count) {
        super(count);
    }

    @Override
    public RankType getRankType() {
        return RankType.FOURTH; //4등
    }

    @Override
    public Long getWinningTotalCount(Long count) {
        return count;
    }

    public Long getFourthAmount(Long count) {
        return count * 50000L;//등위별 당첨금액 : 4등 총 당첨금액
    }
}
