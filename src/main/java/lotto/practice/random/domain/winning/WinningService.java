package lotto.practice.random.domain.winning;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WinningService {

    private final WinningDbRepository winningDbRepository;

    public void getWinner(Long drwNo) {
        LottoApi getThisWeekWinning = winningDbRepository.getThisWeekWinning(drwNo);
        winningDbRepository.getWinner(getThisWeekWinning.getSixBall());
    }

}
