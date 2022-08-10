package lotto.practice.random.domain.Winner.rank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.Winner.RankType;

/**
 * 3등 총 누적 수
 * 3등 :  (총 당첨금 -  i )* 0.125 / 당첨자수
 */
@NoArgsConstructor
@AllArgsConstructor
public class ThirdRank extends Rank {

    private int thirdCount;
    private Long getSubtractAmount;

    public ThirdRank(Long getSubtractAmount) {
        this.getSubtractAmount = getSubtractAmount;
    }

    @Override
    public RankType getRankType() {
        return RankType.THIRD; //3등
    }

    @Override
    public int getRankTotalCount(RankType third) {
        if (third == RankType.THIRD) {
            this.thirdCount += 1;
        }
        return thirdCount;
    }

    public Long getRankAmount() {//4,5등을 제외한 금액 파라미터로 받아서
        return getSubtractAmount * (125 / 1000);
    }

}
