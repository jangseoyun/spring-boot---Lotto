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
@Transactional(readOnly = true)
public class WinningService {

    private final WinningDbRepository winningDbRepository;

    //값을 받아와야 하기때문에 service에서 dto 변환
    public List<MachineCycleStorage> getWinner(Long drwNo) {
        LottoApi getThisWeekWinning = winningDbRepository.getThisWeekWinning(drwNo);
        log.info("getThisWeekWinning = " + getThisWeekWinning);

        List<MachineCycleStorage> winner = winningDbRepository.getWinner(getThisWeekWinning.getSixBall());
        for (int i = 0; i < winner.size(); i++) {
            log.info("winner" + i + "번째" + winner.get(i));
        }

        return winner;
    }

}
