package lotto.practice.random.dto;

import lombok.*;
import lotto.practice.random.domain.machine.dto.LottoCommand;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Set;

/**
 * 사용자 입력 사항
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LottoRequestDto {

    public final static int PRICE = 1000; //lotto 금액

    //사용자가 요청한 타입, 구입금액
    //필드
    @NotNull(message = "userNo는 null일 수 없습니다.")
    private Long userNo;

    @Length(min = 7, max = 9)
    @NotEmpty(message = "번호 추출 타입은 빈값일 수 없습니다.")
    @NotNull(message = "번호 추출 타입은 null일 수 없습니다.")
    private String type;

    @Positive//양수만 가능
    @Max(value = 1000000) //100만원까지 가능
    @NotNull(message = "금액은 null일 수 없습니다.")
    private int price;

    @Positive
    @Size(max = 6)
    private Set<Integer> inputNum;

    private Long storageCycle;

    //요청 회차 (기존 회차 + 1)

    private LottoRequestDto(Long userNo, String type, int buying) {
        this.userNo = userNo;
        this.type = type;

        if ((buying % PRICE) != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요");
        }
        this.price = buying;
    }

    /*private LottoRequestDto(Long userNo, String type, int buying, Set<Integer> inputNum) {
        this.userNo = userNo;
        this.type = type;

        if ((buying % PRICE) != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요");
        }
        this.price = buying;

        if (inputNum == null) {
            throw new IllegalArgumentException("번호를 입력해주세요");
        }
        this.inputNum = inputNum;
    }*/

    //TODO: 빌터 패던으로 변경
    public LottoCommand toCommand() {
        return new LottoCommand(
                this.getUserNo(),
                this.getType(),
                (this.getPrice() / PRICE),//여기서 카운트로 변경ㄹ해줌
                this.getInputNum(),
                this.getStorageCycle()
        );
    }


}
