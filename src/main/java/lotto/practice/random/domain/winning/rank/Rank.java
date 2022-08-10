package lotto.practice.random.domain.winning.rank;

import lotto.practice.random.domain.winning.RankType;

public abstract class Rank {

    abstract public RankType getRankType();//순위

    abstract public int getRankTotalCount(RankType ranktype);//당첨 전체 인원 수

}
