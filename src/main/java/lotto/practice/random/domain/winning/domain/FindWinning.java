package lotto.practice.random.domain.winning.domain;

import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.command.WinnerCommand;

import java.util.List;

public interface FindWinning {
    List<WinnerCommand> getWinnerList(LottoApi getThisWeekWinning, List<MachineCycleStorage> findAllUser);
}
