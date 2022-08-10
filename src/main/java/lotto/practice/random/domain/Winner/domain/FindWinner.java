package lotto.practice.random.domain.Winner.domain;

import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;

import java.util.List;

public interface FindWinner {
    List<WinnerCommand> getWinnerList(LottoApi getThisWeekWinning, List<MachineCycleStorage> findAllUser);
}
