package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
import lotto.practice.random.domain.winningAmount.WinningAmountService;
import lotto.practice.random.domain.winningInfo.WinningInfoCommand;
import lotto.practice.random.domain.winningInfo.WinningMap;
import lotto.practice.random.domain.winningInfo.WinningService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("winning")
public class WinningController {

    private final WinnerService winnerService;
    private final WinningAmountService winningAmountService;

    private final WinningService winningService;

    /**
     * 매주 당첨 결과가 나올 때 실행하는 것 -> 주 1회
     * 이건 시간에 맞춰 작동할 수 있도록...? runtime시? 언제가 좋을 것인가 : 사이트 요청시 시간오래 걸릴 수 있음
     */
    @PostMapping("/checkWinner")
    public List<WinnerCommand> checkWinner(@RequestParam("drwNo") String drwNo) {
        log.info("당첨자 선별 후 저장하기");
        List<WinnerCommand> winnerList = winnerService.saveWinner(drwNo);
        return winnerList;
    }

    /**
     * 사용자가 '요청한 회차' 결과 리스트
     */
    @GetMapping("/result/winner")
    public String requestGameResult(@RequestParam("drwNo") Long lottoCycleNum, Model model) {
        log.info("당첨자 요청 페이지 controller");
        //전체 회차 리스트
        List<Long> cycleNumList = winningService.getCycleNumList();

        //화면에 뿌려줄 winningInfo 가져오기
        WinningMap winningMap = winningAmountService.calcAmount(lottoCycleNum);
        List<WinningInfoCommand> resultWinInfoList = winningService.makeWinningInfo(winningMap);

        //map에 담아서 회차 리스트랑 같이 보내기 -> 이 경우 리스트를 계속 요청하게 되는데 다른 방법이 있나요
        Map<String, Object> resultWinnerMap = new HashMap<>();
        resultWinnerMap.put("cycleNumList", cycleNumList);
        resultWinnerMap.put("resultWinInfoList", resultWinInfoList);
        model.addAttribute("resultWinnerMap", resultWinnerMap);

        return ""; //TODO: 화면 만들어서 보내주기
    }



}
