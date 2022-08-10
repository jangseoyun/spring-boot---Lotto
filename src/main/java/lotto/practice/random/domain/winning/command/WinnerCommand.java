package lotto.practice.random.domain.winning.command;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.winning.RankType;

import java.time.LocalDateTime;

@Slf4j
@Data
@NoArgsConstructor
public class WinnerCommand {

    private String sixBall; //당첨 번호
    private Ball bonusBall;
    private String winAllNum;
    private User user; //당첨자
    private RankType winnerRank;         //당첨 순위
    private LottoApi lottoCycleNum;   //로또 회차 번호
    private LocalDateTime winDate;  //당첨일

    @Builder
    public WinnerCommand(String sixBall, Ball bonusBall, User user, RankType winnerRank, LottoApi lottoCycleNum, LocalDateTime winDate) {
        this.sixBall = sixBall;
        this.bonusBall = bonusBall;
        this.winAllNum = toString();
        this.user = user;
        this.winnerRank = winnerRank;
        this.lottoCycleNum = lottoCycleNum;
        this.winDate = winDate;
    }

    @Override
    public String toString() {
        return getSixBall() + "bonusNum + " + getBonusBall().getValue();
    }

}
