package lotto.practice.random.domain.winningAmount.rank;

public class TotalCountFactory {

    public static TotalCount setRankTotalCount(Long fifthTotalCount, Long fourthTotalCount, Long thirdTotalCount, Long secondTotalCount, Long firstTotalCount) {
        return TotalCount.builder()
                .fifthTotalCount(fifthTotalCount)
                .fourthTotalCount(fourthTotalCount)
                .thirdTotalCount(thirdTotalCount)
                .secondTotalCount(secondTotalCount)
                .firstTotalCount(firstTotalCount)
                .build();
    }

}
