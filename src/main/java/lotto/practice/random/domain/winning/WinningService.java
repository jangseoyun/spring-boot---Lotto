package lotto.practice.random.domain.winning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.dto.WinningDto;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        log.info("회차 당첨 번호 = " + getThisWeekWinning);

        List<MachineCycleStorage> findAllUser = winningDbRepository.findAllUser();
        log.info("이번회차 사용자 리스트 = " + findAllUser);

        List<WinningDto> winnerList = findWinning.getWinnerList(getThisWeekWinning, findAllUser);
        log.info("이번회차 당첨자 정보 리스트 = " + winnerList);

        Map<String, Long> winningAmount = findWinning.calculateAmount(getThisWeekWinning.getTotSellamnt());
        log.info("등위별 총당첨 금액 = " + winningAmount);

        List<Integer> totalCount = findWinning.makeTotalCount();
        log.info("totalCount = " + totalCount);

        //winner 등록
        for (WinningDto winner : winnerList) {
            WinningFactory.createWinningInfo(winner, winningAmount, totalCount);
        }

        return findAllUser;
    }



}
