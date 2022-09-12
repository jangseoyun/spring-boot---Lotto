package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApiService;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winner.WinnerService;
import lotto.practice.random.domain.winner.command.WinnerCommand;
import lotto.practice.random.domain.winningAmount.WinningAmountService;
import lotto.practice.random.domain.winningInfo.WinningInfo;
import lotto.practice.random.domain.winningInfo.WinningResult;
import lotto.practice.random.domain.winningInfo.WinningService;
import lotto.practice.random.dto.LottoApiDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final LottoApiService lottoApiService;

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
    public String getCycleWinningInfo2(@RequestParam("lottoCycleNum") Long lottoCycleNum, Model model) {
        //회차 리스트
        List<Long> cycleNumList = winningService.getCycleNumList();
        //요청회차 당첨 번호 API
        LottoApiDto lottoSingleResult = lottoApiService.getLottoResult(lottoCycleNum);
        //당첨자 리스트
        List<WinningInfo> getCycleWinningInfo = winningService.findWinningInfo(lottoCycleNum);
        log.info("getCycleWinningInfo = " + getCycleWinningInfo);

        model.addAttribute("getCycleWinningInfo", getCycleWinningInfo);
        model.addAttribute("lottoSingleResult", lottoSingleResult);
        model.addAttribute("cycleNumList", cycleNumList);
        return "result/cycle-result-page";
    }

    @GetMapping("/result/game2")
    public String getCycleWinningInfo(@RequestParam("lottoCycleNum") Long lottoCycleNum, Model model) {
        List<WinningInfo> getCycleWinningInfo = winningService.findWinningInfo(lottoCycleNum);
        log.info("getCycleWinningInfo = " + getCycleWinningInfo);
        model.addAttribute("getCycleWinningInfo", getCycleWinningInfo);
        return "result/cycle-result-page";
    }

    @GetMapping("/result/user/{userNo}")
    public String userLottoResult(@PathVariable("userNo") Long userNo
            , @RequestParam("lottoCycleNum") Long lottoCycleNum
            , Model model) {
        //전체 회차 리스트
        List<Long> cycleNumList = winningService.getCycleNumList();
        model.addAttribute("cycleNumList", cycleNumList);

        //TODO: 당첨 여부 확인
        winningService.userGameResult();

        //사용자 이번주 추첨 번호 리스트
        List<MachineCycleStorage> userCycleStorage = winningService.getUserCycleStorage(lottoCycleNum, userNo);
        if (userCycleStorage == null) {
            model.addAttribute("userStorage", "storageEmpty");
        }

        model.addAttribute("userStorage", userCycleStorage);

        return "result/user-result-page";
    }


}
