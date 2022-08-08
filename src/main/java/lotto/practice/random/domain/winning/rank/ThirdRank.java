package lotto.practice.random.domain.winning.rank;

import lotto.practice.random.domain.winning.RankType;

/**
 * 3등 총 누적 수
 */
public class ThirdRank extends Rank {

    public ThirdRank(Long count) {
        super(count);
    }

    @Override
    public RankType getRankType() {
        return RankType.THIRD; //3등
    }

    @Override
    public Long getWinningTotalCount(Long count) {//3등 당첨 누적 총 인원수
        return count;
    }
}
