package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.winningAmount.WinningAmountService;
import lotto.practice.random.domain.winningInfo.WinningInfo;
import lotto.practice.random.domain.winningInfo.WinningResult;
import lotto.practice.random.domain.winningInfo.WinningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("winning")
public class WinningController {

    private final WinnerService winnerService;
    private final WinningAmountService winningAmountService;
    private final WinningService winningService;

    /**
     * test용 WinnerSchedule.class -> schedule로 실행
     */
    @GetMapping("/check-winner")
    public void checkWinner(@RequestParam("lottoCycleNum") Long lottoCycleNum) {
        log.info("당첨자 선별 후 저장하기");
        List<WinnerCommand> winnerList = winnerService.saveWinner(lottoCycleNum);

        //화면에 뿌려줄 winningInfo 저장하기
        WinningResult winningResult = winningAmountService.calcAmount(lottoCycleNum);
        winningService.makeWinningInfo(winningResult);
    }

    /**
     * ****************************************************
     * 회차별 당첨 내용 요청
     */
    @GetMapping("/result/game")
    public List<WinningInfo> getCycleWinningInfo(@RequestParam("lottoCycleNum") Long lottoCycleNum) {
        List<WinningInfo> getCycleWinningInfo = winningService.findWinningInfo(lottoCycleNum);
        log.info("getCycleWinningInfo = " + getCycleWinningInfo);
        return getCycleWinningInfo;
    }


}
