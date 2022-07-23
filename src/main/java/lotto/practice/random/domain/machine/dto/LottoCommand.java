package lotto.practice.random.domain.machine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class LottoCommand {

    //사용자가 요청한 타입, 구입금액
    //필드
    private Long userNo;
    private Lottotype lottotype;//TODO: 이넘타입변경하기
    private int count;
    private Set<Integer> inputNum;
    private Long storageCycle;

}
