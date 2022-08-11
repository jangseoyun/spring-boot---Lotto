package lotto.practice.random.domain.Winner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.Winner.domain.FindWinner;
import lotto.practice.random.domain.Winner.entity.Winner;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.infrastructure.WinnerDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WinnerService {

    private final WinnerDbRepository winnerDbRepository;
    private final FindWinner findWinning;


    /**
     * 이번주 회차 번호 가지고 오기
     */
    public Long getLastCycleNum() {
        Long getLastCycelNum = winnerDbRepository.getCycleNum();
        log.info("이번주 회차 번호 : " + getLastCycelNum);
        return getLastCycelNum;
    }

    /**
     * 1. 회차별 당첨자 선별
     * 2. 당첨자 DB 저장 (t_winner)
     */
    @Transactional
    public List<WinnerCommand> saveWinner(String drwNo) {
        LottoApi getThisWeekWinning = winnerDbRepository.getThisWeekWinning(drwNo);
        log.info("회차 당첨 번호 = " + getThisWeekWinning);

        List<MachineCycleStorage> findAllUser = winnerDbRepository.findAllUser();
        log.info("이번회차 사용자 리스트 = " + findAllUser);

        //당첨자 찾기
        List<WinnerCommand> winnerList = findWinning.getWinnerList(getThisWeekWinning, findAllUser);
        log.info("이번회차 당첨자 정보 리스트 = " + winnerList);

        for (WinnerCommand winner : winnerList) {
            Winner getWinner = WinnerFactory.toWinner(winner);
            winnerDbRepository.saveWinner(getWinner);
        }

        return winnerList;
    }


}
