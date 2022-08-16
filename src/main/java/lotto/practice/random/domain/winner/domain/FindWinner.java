package lotto.practice.random.domain.winner.domain;

import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winner.command.WinnerCommand;

import java.util.List;

public interface FindWinner {
    List<WinnerCommand> getWinnerList(LottoApi getThisWeekWinning, List<MachineCycleStorage> findAllUser);
}
