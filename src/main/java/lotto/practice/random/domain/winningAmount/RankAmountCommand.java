package lotto.practice.random.domain.winningAmount;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankAmountCommand {

    private Long fifthAmount;
    private Long fourthAmount;
    private Long thirdNsecondAmount;
    private Long firstAmount;

    public RankAmountCommand(Long fifthAmount, Long fourthAmount, Long thirdNsecondAmount, Long firstAmount) {
        this.fifthAmount = fifthAmount;
        this.fourthAmount = fourthAmount;
        this.thirdNsecondAmount = (thirdNsecondAmount * 2);
        this.firstAmount = firstAmount;
    }
}
