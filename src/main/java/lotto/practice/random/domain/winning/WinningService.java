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
    private final FindWinning findWinning;

    //회차별 당첨 번호 로직으로도 사용할 수 있음
    public List<MachineCycleStorage> getWinner(Long drwNo) {
        LottoApi getThisWeekWinning = winningDbRepository.getThisWeekWinning(drwNo);
        log.info("이번주 당첨 번호 = " + getThisWeekWinning);

        List<MachineCycleStorage> findAllUser = winningDbRepository.findAllUser();
        log.info("추첨한 사용자 리스트 = " + findAllUser);

        WinningInfo winner = findWinning.findWinner(getThisWeekWinning, findAllUser);
        log.info("당첨자 정보 = " + winner);


        //만약 winner가 있다면 iter돌려서 winnerInfo 테이블에 넣어준다
        //당첨자 winning_info 테이블에 저장
        /*createWinnerInfo();
        winningDbRepository.saveWinner();

        return winner;*/
    }



}
