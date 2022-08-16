package lotto.practice.random.domain.Winner.ranktype;

import lotto.practice.random.domain.Winner.RankType;

public class FailRank extends Rank {
    @Override
    public RankType getRankType() {
        return RankType.FAIL;
    }
}
