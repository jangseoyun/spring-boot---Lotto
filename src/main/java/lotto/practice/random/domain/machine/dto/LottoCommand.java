package lotto.practice.random.domain.machine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lotto.practice.random.domain.machine.Ball;

@Value
@Builder
@AllArgsConstructor
public class LottoCommand {

    //사용자가 요청한 타입, 구입금액
    //필드
    private Long userNo;
    private Lottotype lottotype; //enum 타입
    private int count;
    private Ball inputNum;
    private Long storageCycle;

}
