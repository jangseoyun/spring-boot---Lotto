package lotto.practice.random.domain.winning;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.winning.command.WinnerCommand;
import lotto.practice.random.domain.winning.entity.Winner;

@Slf4j
public class WinningFactory {

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
