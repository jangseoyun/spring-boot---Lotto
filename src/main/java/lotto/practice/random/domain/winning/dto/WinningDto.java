package lotto.practice.random.domain.winning.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.user.User;

@Slf4j
@Data
@NoArgsConstructor
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

    private String winAllNum;

    @Builder
    public WinningDto(String sixBall, Long totSellingPrice, User user, Long lottoCycleNum, int winnerRank, Ball bonusBall, String winAllNum) {
        this.sixBall = sixBall;
        this.totSellingPrice = totSellingPrice;
        this.user = user;
        this.lottoCycleNum = lottoCycleNum;
        this.winnerRank = winnerRank;
        this.bonusBall = bonusBall;
        this.winAllNum = toString();
    }

    @Override
    public String toString() {
        return getSixBall() + "bonusNum + " + getBonusBall().getValue();
    }


}
