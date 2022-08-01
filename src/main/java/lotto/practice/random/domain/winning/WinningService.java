package lotto.practice.random.domain.winning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WinningService {

    private final WinningDbRepository winningDbRepository;

    public void getWinner(Long drwNo) {
        LottoApi getThisWeekWinning = winningDbRepository.getThisWeekWinning(drwNo);
        log.info("getThisWeekWinning" + getThisWeekWinning);

        List<MachineCycleStorage> winner = winningDbRepository.getWinner(getThisWeekWinning.getSixBall());
        log.info("winner" + winner);
    }

}
