package lotto.practice.random.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.machine.dto.LottoCommand;
import lotto.practice.random.domain.machine.dto.Lottotype;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;

/**
 * 사용자 입력 사항
 */
@Data
@Builder(access = AccessLevel.PROTECTED)
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
    private Lottotype lottotype;

    @Positive//양수만 가능
    @Max(value = 1000000) //100만원까지 가능
    @NotNull(message = "금액은 null일 수 없습니다.")
    private int price;

    @Positive
    private String inputNum;//스트링으로 받아서 자르기

    private Long storageCycle;

    //요청 회차 (기존 회차 + 1)

    private LottoRequestDto(Long userNo, Lottotype lottotype, int buying) {
        this.userNo = userNo;
        this.lottotype = lottotype;

        if ((buying % PRICE) != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요");
        }
        this.price = buying;
    }

    public LottoRequestDto(Long userNo, Lottotype lottotype, int price, String inputNum, Long storageCycle) {
        this.userNo = userNo;
        this.lottotype = lottotype;
        this.price = price;
        this.inputNum = inputNum;
        //TODO: 생성자에서 변경하고 Ball로 반환하기
        this.storageCycle = storageCycle;
    }

    //request DTO-> command object
    public LottoCommand toCommand(LottoRequestDto lottoRequestDto) {

        List<String> strings = Arrays.asList(lottoRequestDto.getInputNum().split(",")); //string 잘라서 하나씩 list에 담기
        Ball ball = new Ball();

        for (String string : strings) {
            ball = new Ball(Integer.parseInt(string));//Ball로 변환하여 보냄
        }

        return LottoCommand.builder()
                .userNo(lottoRequestDto.getUserNo())
                .lottotype(lottoRequestDto.getLottotype())
                .count(lottoRequestDto.getPrice() / PRICE)//구입한 갯수 계산해서 넘김
                .inputNum(ball)
                .storageCycle(lottoRequestDto.getStorageCycle())
                .build();
    }


}
