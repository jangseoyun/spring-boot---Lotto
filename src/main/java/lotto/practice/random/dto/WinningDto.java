package lotto.practice.random.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.user.User;

@Slf4j
@Data
public class WinningDto {

    private Long apiNo;
    private String sixBall;
    private Long totSellamnt;       //누적 상금
    private Long firstWinamnt;      //1등 당첨액
    private Long firstPrzwnerCo;    //1등 당첨인원
    private Long firstAccumamnt;    //1등 총액
    private Long drwNo;             //로또 회차

    private User user;
    private Long storageCycle;
    private String storageDate;
}
