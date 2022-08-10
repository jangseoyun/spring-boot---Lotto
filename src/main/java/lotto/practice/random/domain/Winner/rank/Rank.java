package lotto.practice.random.domain.Winner.rank;

import lotto.practice.random.domain.Winner.RankType;

public abstract class Rank {

    abstract public RankType getRankType();//순위

    abstract public int getRankTotalCount(RankType ranktype);//당첨 전체 인원 수

}
