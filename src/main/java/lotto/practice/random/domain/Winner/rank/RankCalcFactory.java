package lotto.practice.random.domain.Winner.rank;

public class RankCalcFactory {

    public static RankCommand createRankList(FifthRank fifth, FourthRank fourth, ThirdRank third, SecondRank second, FirstRank first) {
        return RankCommand.builder()
                .fifth(fifth)
                .fourth(fourth)
                .third(third)
                .second(second)
                .first(first)
                .build();
    }


}
