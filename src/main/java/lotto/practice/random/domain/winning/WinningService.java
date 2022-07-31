package lotto.practice.random.domain.winning;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WinningService {

    private final WinningDbRepository winningDbRepository;

    public void getWinner(Long drwNo) {
        winningDbRepository.getThisWeekWinning(drwNo);
    }

}
