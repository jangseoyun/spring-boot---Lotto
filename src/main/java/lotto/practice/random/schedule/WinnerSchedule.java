package lotto.practice.random.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.winningAmount.WinningAmountService;
import lotto.practice.random.domain.winningInfo.WinningResult;
import lotto.practice.random.domain.winningInfo.WinningService;
import org.springframework.stereotype.Component;

import java.util.List;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;
import static lotto.practice.random.common.MemoryContext.memory;

@Slf4j
@Component
@RequiredArgsConstructor
public class WinnerSchedule {

    private final WinnerService winnerService;
    private final WinningAmountService winningAmountService;
    private final WinningService winningService;

    //@Scheduled(cron = "* * 22 * * SAT")
    //@Scheduled(cron = "0 * * * * *")
    public void checkWeekWinner() {
        log.info("당첨자 선별 후 저장하기");
        Long lastCycleNum = memory.get(LAST_CYCLE_NUM);
        List<WinnerCommand> winnerList = winnerService.saveWinner(lastCycleNum);

        log.info("화면에 뿌려줄 winningInfo 저장하기");
        WinningResult winningMap = winningAmountService.calcAmount(lastCycleNum);
        winningService.makeWinningInfo(winningMap);
    }
}
