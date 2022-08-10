package lotto.practice.random.domain.winning.rank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.winning.RankType;

/**
 * 2등 총 당첨자 수
 * 2등 :  (총 당첨금 - i ) * 0.125
 */
@NoArgsConstructor
@AllArgsConstructor
public class SecondRank extends Rank {

    private int secondCount;
    private Long getSubtractAmount;

    public SecondRank(Long getSubtractAmount) {
        this.getSubtractAmount = getSubtractAmount;
    }

    @Override
    public RankType getRankType() {
        return RankType.SECOND;
    }

    @Override
    public int getRankTotalCount(RankType second) {
        if (second == RankType.SECOND) {
            this.secondCount += 1;
        }
        return secondCount;
    }

    public Long getRankAmount() {
        return getSubtractAmount * (125 / 1000);
    }

}
