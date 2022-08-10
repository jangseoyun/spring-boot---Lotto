package lotto.practice.random.domain.winning.rank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.winning.RankType;

/**
 * 1등 총 당첨자 수
 * 1등 :  (총 당첨금 - i ) * 0.75
 */
@NoArgsConstructor
@AllArgsConstructor
public class FirstRank extends Rank {

    private int firstCount;
    private Long getSubtractAmount;

    public FirstRank(Long getSubtractAmount) {
        this.getSubtractAmount = getSubtractAmount;
    }

    @Override
    public RankType getRankType() {
        return RankType.FIRST;
    }

    @Override
    public int getRankTotalCount(RankType first) {
        if (first == RankType.FIRST) {
            this.firstCount += 1;
        }
        return firstCount;
    }

    public Long getRankAmount() {
        return getSubtractAmount * (3 / 4);
    }

}
