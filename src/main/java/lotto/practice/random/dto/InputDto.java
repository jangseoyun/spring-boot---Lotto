package lotto.practice.random.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;

/**
 * 사용자 입력 사항
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InputDto {

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
    private int buying;

    @Positive
    @Size(max = 6)
    private String[] inputNum;

    //요청 회차 (기존 회차 + 1)

    private InputDto(Long userNo, String type, int buying) {
        this.userNo = userNo;
        this.type = type;

        if ((buying%PRICE) != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요");
        }
        this.buying = buying;
    }

    private InputDto(Long userNo, String type, int buying, String inputNum) {
        this.userNo = userNo;
        this.type = type;

        if ((buying%PRICE) != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요");
        }
        this.buying = buying;

        if(inputNum == null) {
            throw new IllegalArgumentException("번호를 입력해주세요");
        }
        String[] splitInputNum = StringUtils.split(inputNum, ",");
        this.inputNum = splitInputNum;
    }



}
