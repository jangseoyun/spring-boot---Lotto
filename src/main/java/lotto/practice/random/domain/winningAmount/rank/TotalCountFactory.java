package lotto.practice.random.domain.winningAmount.rank;

public class TotalCountFactory {

    public static TotalCount setRankTotalCount(int fifthTotalCount, int fourthTotalCount, int thirdTotalCount, int secondTotalCount, int firstTotalCount) {
        return TotalCount.builder()
                .fifthTotalCount(fifthTotalCount)
                .fourthTotalCount(fourthTotalCount)
                .thirdTotalCount(thirdTotalCount)
                .secondTotalCount(secondTotalCount)
                .firstTotalCount(firstTotalCount)
                .build();
    }

}
