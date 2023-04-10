package lotto.practice.random.domain.winner.ranktype;

import lotto.practice.random.domain.winner.RankType;

public class FailRank extends Rank {
    @Override
    public RankType getRankType() {
        return RankType.FAIL;
    }
}
