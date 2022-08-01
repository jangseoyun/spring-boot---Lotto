package lotto.practice.random.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.dto.LottoCommand;
import lotto.practice.random.domain.machine.dto.Lottotype;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 사용자 입력 사항
 */
@Slf4j
@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LottoRequestDto {

    public final static int PRICE = 1000; //lotto 금액
    private static final int MAX = 45;

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
    private LottoRequestDto(Long userNo, Lottotype lottotype, int price, Long storageCycle) {
        this.userNo = userNo;
        this.lottotype = lottotype;
        validBuying(price);
        this.price = price;
        this.storageCycle = storageCycle;
    }

    public LottoRequestDto(Long userNo, Lottotype lottotype, int price, String inputNum, Long storageCycle) {
        this.userNo = userNo;
        this.lottotype = lottotype;
        validBuying(price);
        this.price = price;
        this.inputNum = inputNum;

        this.storageCycle = storageCycle;
    }

    //request DTO-> command object
    public LottoCommand toCommand(LottoRequestDto lottoRequestDto) {
        //inputNum이 있는 경우와 없는 경우..
        if (lottoRequestDto.inputNum == null) {
            LottoCommand inputNumBuild = LottoCommand.builder()
                    .userNo(lottoRequestDto.getUserNo())
                    .lottotype(lottoRequestDto.getLottotype())
                    .count(lottoRequestDto.getPrice() / PRICE)//구입한 갯수 계산해서 넘김
                    .storageCycle(lottoRequestDto.getStorageCycle())
                    .build();
            return inputNumBuild;
        }

        return LottoCommand.builder()
                .userNo(lottoRequestDto.getUserNo())
                .lottotype(lottoRequestDto.getLottotype())
                .count(lottoRequestDto.getPrice() / PRICE)//구입한 갯수 계산해서 넘김
                .inputNum(changeInputNum(lottoRequestDto.getInputNum()))
                .storageCycle(lottoRequestDto.getStorageCycle())
                .build();

    }

    /**
     * 사용자가 구입한 화폐단위가 범위에 들어온지 검증
     */
    private void validBuying(int buying) {
        if ((buying % PRICE) != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요");
        }
    }

    /**
     * 사용자가 입력한 로또 번호 Ball로 변경
     * - 화면에서 리스트로 받는 방법
     * - string으로 받아서 잘라서 사용하는 방법 **
     * - 번호 타입 자체를 문자열로 변경하는 방법
     */
    private List<Integer> changeInputNum(String inputNum) {
        List<Integer> inputNumList = new ArrayList(); //ball 담을 리스트 생성

        //TODO: string을 번호 순서대로 넣을 수 있도록(list로 각각 넣어야하나..)
        List<String> splitInputNum = Arrays.asList(inputNum.split(",")); //1.string 잘라서 하나씩 list에 담기

        for (String inputNumVo : splitInputNum) {
            Integer changeIntNum = Integer.parseInt(inputNumVo);//2.string -> Integer로 변경
            inputNumList.add(numValidScope(changeIntNum));//3.범위 검증
        }
        if (this.lottotype == Lottotype.ALLSELECT) {
            checkLength(inputNumList);
        }

        log.info("inputNumList = " + inputNumList);
        return inputNumList;
    }

    /**
     * 사용자가 입력한 번호 범위 검증
     */
    private Integer numValidScope(Integer changeIntNum) {
        if (changeIntNum < 1 || changeIntNum > MAX) {
            throw new IllegalArgumentException("1~45 사이의 숫자만 가능합니다");
        }
        return changeIntNum;
    }

    /**
     * Allselect 전체수동의 경우 6개씩 한묶음으로 번호를 받야한다
     * 1티켓당 5천원까지 => 최대 30개의 숫자
     */
    private void checkLength(List<Integer> inputNumList) {
        //TODO: 긍정문으로 변경할 수 있도록 로직을 생각해볼 것
        if ((inputNumList.size() % 6) != 0) {
            throw new ArrayIndexOutOfBoundsException("6개의 숫자를 모두 입력해주세요");
        }
    }


}
