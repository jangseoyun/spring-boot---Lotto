package lotto.practice.random.domain.Winner;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.Winner.entity.Winner;

@Slf4j
public class WinnerFactory {

    public static Winner toWinner(WinnerCommand winnerCommand) {
        return Winner.builder()
                .sixBall(winnerCommand.getSixBall())
                .bonusBall(winnerCommand.getBonusBall())
                .winAllNum(winnerCommand.getWinAllNum())
                .user(winnerCommand.getUser())
                .winnerRank(winnerCommand.getWinnerRank())
                .lottoCycleNum(winnerCommand.getLottoCycleNum())
                .build();
    }

}
