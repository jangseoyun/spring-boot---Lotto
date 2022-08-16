package lotto.practice.random.domain.winningInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.entity.Winner;
import lotto.practice.random.infrastructure.WinnerDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WinningService {

    private final WinnerDbRepository winnerDbRepository;
    private final WinningInfoJpaRepository winningInfoJpaRepository;

    /**
     * 회차 전체 리스트 가져오기
     */
    public List<Long> getCycleNumList() {
        List<Long> cycleNumList = winnerDbRepository.getCycleNumList();
        log.info("회차 전체 리스트 : " + cycleNumList);
        return cycleNumList;
    }

    /**
     * 화면에 보낼 winningInfo 만들기
     */
    public List<WinningInfoCommand> makeWinningInfo(WinningMap winningMap) {
        List<WinningInfoCommand> resultWinInfo = new ArrayList<>();

        //winnerList 가지고 오기 winner + 등수에 따라 RankAmountCommand 필요
        List<Winner> allWinner = winnerDbRepository.findAllWinner();
        for (Winner winner : allWinner) {
            WinningInfoCommand createWinningInfo = WinningInfoFactory.setWinningInfo(winner, winningMap);
            resultWinInfo.add(createWinningInfo);
        }
        return resultWinInfo;
    }

    /**
     * 회차별 winningInfo 가져오기
     */
    public List<WinningInfo> findWinningInfo(Long lottoCycleNum) {
        return winningInfoJpaRepository.findBylottoCycleNum(lottoCycleNum);
    }

}
