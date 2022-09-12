package lotto.practice.random.domain.lottoapi;

import lotto.practice.random.dto.LottoApiDto;

public class LottoApiFactory {

    public static LottoApiDto toLottoApiDto(LottoApi getLottoResult) {
        return LottoApiDto.builder()
                .cycleNum(getLottoResult.getDrwNo())
                .ball1(getLottoResult.getDrwtNo1())
                .ball2(getLottoResult.getDrwtNo2())
                .ball3(getLottoResult.getDrwtNo3())
                .ball4(getLottoResult.getDrwtNo4())
                .ball5(getLottoResult.getDrwtNo5())
                .ball6(getLottoResult.getDrwtNo6())
                .bonusBall(getLottoResult.getBnusNo())
                .totSellamnt(getLottoResult.getTotSellamnt())
                .build();
    }

}
