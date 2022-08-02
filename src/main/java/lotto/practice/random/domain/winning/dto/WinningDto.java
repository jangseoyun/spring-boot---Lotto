package lotto.practice.random.domain.winning.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.user.User;

@Slf4j
@Data
public class WinningDto {

    private String sixBall; //당첨 번호

    private Long totSellingPrice;  //총 판매액

    private User user; //당첨자

    private Long lottoCycleNum;     //로또 회차

    private int winnerRank;         //당첨 순위
    private Long totalAmount;       //등위별 총 당첨금액
    private Long winnerAmount;      //1게임당 당첨금액
    private Long winnerTotalCount;    //해당순위 총 당첨인원
    private Ball bonusBall;

    @Builder
    public WinningDto(String sixBall, Long totSellingPrice, User user, Long lottoCycleNum, int winnerRank, Long totalAmount, Long winnerAmount, Long winnerTotalCount, Ball bonusBall) {
        this.sixBall = sixBall;
        this.totSellingPrice = totSellingPrice;
        this.user = user;
        this.lottoCycleNum = lottoCycleNum;
        this.winnerRank = winnerRank;
        this.bonusBall = bonusBall;

        this.totalAmount = totalAmount;
        this.winnerAmount = winnerAmount;
        this.winnerTotalCount = winnerTotalCount;
    }

    //등위별 총 당첨금액 계산
    /**
     * 1등 :  (총 당첨금 - i ) * 0.75
     * 2등 :  (총 당첨금 - i ) * 0.125
     * 3등 :  (총 당첨금 -  i )* 0.125
     * 4등 : 전체 금액 - (50,000)
     * 5등 : 전체 금액 - (5,000)
     * * 4 + 5등의 합 = i
     */


    //DTO 검증 로직
    //당첨된 사람만 입력하는 로직 !

}
