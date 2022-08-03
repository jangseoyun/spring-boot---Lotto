package lotto.practice.random.domain.winning.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
public class TotalCount {


    private int firstTotalCount;
    private int secondTotalCount;
    private int thirdTotalCount;
    private int fourthTotalCount;
    private int fifthTotalCount;

    public TotalCount(int firstTotalCount, int secondTotalCount, int thirdTotalCount, int fourthTotalCount, int fifthTotalCount) {
        this.firstTotalCount = firstTotalCount;
        this.secondTotalCount = secondTotalCount;
        this.thirdTotalCount = thirdTotalCount;
        this.fourthTotalCount = fourthTotalCount;
        this.fifthTotalCount = fifthTotalCount;
    }


}
