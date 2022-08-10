package lotto.practice.random.domain.winning.rank;

import lombok.NoArgsConstructor;
import lotto.practice.random.domain.winning.RankType;

/**
 * 4등 총 당첨자 수
 * 4등 총 당첨 금액
 */
@NoArgsConstructor
public class FourthRank extends Rank {

    private int fourthCount;

    @Override
    public RankType getRankType() {
        return RankType.FOURTH; //4등
    }

    @Override
    public int getRankTotalCount(RankType fourth) {
        if (fourth == RankType.FOURTH) {
            this.fourthCount += 1;
        }
        return fourthCount;
    }

    public Long getRankAmount() {
        return fourthCount * 50000L;
    }

}
