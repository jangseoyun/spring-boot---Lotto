package lotto.practice.random.domain.Winner.rank;

import lombok.NoArgsConstructor;
import lotto.practice.random.domain.Winner.RankType;

/**
 * 5등 총 당첨자 수
 * 인원 * 상금 : 5등 총 당첨 금액
 * 5등 총 당첨자 수
 */
@NoArgsConstructor
public class FifthRank extends Rank {

    protected int fifthCount;

    @Override
    public RankType getRankType() {//5등
        return RankType.FIFTH;
    }

    @Override
    public int getRankTotalCount(RankType fifth) {//5등 당첨 누적 총 인원수
        if (fifth == RankType.FIFTH) {
            this.fifthCount += 1;
        }
        return fifthCount;
    }


    public Long getRankAmount() {
        return fifthCount * 5000L;
    }

}
