package lotto.practice.random.domain.winning.rank;

import lotto.practice.random.domain.winning.RankType;

public class FailRank extends Rank {

    public FailRank() {
        throw new OutOfMemoryError("순위 등록을 실패하였습니다");
    }

    @Override
    public RankType getRankType() {
        return RankType.FAIL;
    }

    @Override
    public int getRankTotalCount(RankType ranktype) {
        return 0;
    }


}
