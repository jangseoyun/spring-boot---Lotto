package lotto.practice.random.domain.winner.ranktype;

import lotto.practice.random.domain.winner.RankType;

public class FirstRank extends Rank {
    @Override
    public RankType getRankType() {
        return RankType.FIRST;
    }
}
