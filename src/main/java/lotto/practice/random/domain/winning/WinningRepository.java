package lotto.practice.random.domain.winning;

import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;

import java.util.List;

public interface WinningRepository {
    //이번주 회차 데이터 가져오기
    LottoApi getThisWeekWinning(Long drwNo);

    //cycleStorage에서 sixNum과 일치하는 데이터 가져옴
    List<MachineCycleStorage> getWinner(String sixNum);
}
