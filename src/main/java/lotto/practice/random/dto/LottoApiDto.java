package lotto.practice.random.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LottoApiDto {

    private Long cycleNum;
    private int ball1;
    private int ball2;
    private int ball3;
    private int ball4;
    private int ball5;
    private int ball6;
    private int bonusBall;
    private Long totSellamnt;

    @Builder
    public LottoApiDto(Long cycleNum, int ball1, int ball2, int ball3, int ball4, int ball5, int ball6, int bonusBall, Long totSellamnt) {
        this.cycleNum = cycleNum;
        this.ball1 = ball1;
        this.ball2 = ball2;
        this.ball3 = ball3;
        this.ball4 = ball4;
        this.ball5 = ball5;
        this.ball6 = ball6;
        this.bonusBall = bonusBall;
        this.totSellamnt = totSellamnt;
    }
}
