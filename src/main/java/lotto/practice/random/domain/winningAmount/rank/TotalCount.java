package lotto.practice.random.domain.winningAmount.rank;

import lombok.Builder;

public class TotalCount {

    private int fifthTotalCount;
    private int fourthTotalCount;
    private int ThirdTotalCount;
    private int secondTotalCount;
    private int firstTotalCount;

    @Builder
    public TotalCount(int fifthTotalCount, int fourthTotalCount, int thirdTotalCount, int secondTotalCount, int firstTotalCount) {
        this.fifthTotalCount = fifthTotalCount;
        this.fourthTotalCount = fourthTotalCount;
        ThirdTotalCount = thirdTotalCount;
        this.secondTotalCount = secondTotalCount;
        this.firstTotalCount = firstTotalCount;
    }
}
