package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.winningAmount.WinningAmountService;
import lotto.practice.random.domain.winningInfo.WinningInfo;
import lotto.practice.random.domain.winningInfo.WinningInfoCommand;
import lotto.practice.random.domain.winningInfo.WinningMap;
import lotto.practice.random.domain.winningInfo.WinningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
     * 매주 1회 당첨자 선별 로직 실행 : @scheduled
     */
    @PostMapping("/checkWinner")
    public List<WinnerCommand> checkWinner(@RequestParam("lottoCycleNum") String lottoCycleNum) {
        log.info("당첨자 선별 후 저장하기");
        List<WinnerCommand> winnerList = winnerService.saveWinner(lottoCycleNum);
        return winnerList;
    }

    /**
     * 사용자가 '요청한 회차' 결과 리스트
     */
    @GetMapping("/result/winner")
    public String requestGameResult(@RequestParam("lottoCycleNum") Long lottoCycleNum) {
        log.info("당첨자 요청 페이지 controller");
        //전체 회차 리스트
        List<Long> cycleNumList = winningService.getCycleNumList();

        //화면에 뿌려줄 winningInfo 가져오기
        WinningMap winningMap = winningAmountService.calcAmount(lottoCycleNum);
        List<WinningInfoCommand> resultWinInfoList = winningService.makeWinningInfo(winningMap);

        return "SUCCESS"; //TODO: 화면 만들어서 보내주기
    }

    /**
     * ****************************************************
     * 회차별 당첨 내용 요청
     */
    @GetMapping("/result/game")
    public List<WinningInfo> getCycleWinningInfo(@RequestParam("lottoCycleNum") Long lottoCycleNum) {
        List<WinningInfo> getCycleWinningInfo = winningService.findWinningInfo(lottoCycleNum);
        return getCycleWinningInfo;
    }


}
