package lotto.practice.random.domain.winningAmount.rank;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TotalCount {

    private Long fifthTotalCount;
    private Long fourthTotalCount;
    private Long ThirdTotalCount;
    private Long secondTotalCount;
    private Long firstTotalCount;

    @Builder
    public TotalCount(Long fifthTotalCount, Long fourthTotalCount, Long thirdTotalCount,
                      Long secondTotalCount, Long firstTotalCount) {
        this.fifthTotalCount = fifthTotalCount;
        this.fourthTotalCount = fourthTotalCount;
        this.ThirdTotalCount = thirdTotalCount;
        this.secondTotalCount = secondTotalCount;
        this.firstTotalCount = firstTotalCount;
    }


}
