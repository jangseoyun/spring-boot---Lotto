package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.Winner.command.WinnerCommand;
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

    private final WinnerService winningService;

    /**
     * 매주 당첨 결과가 나올 때 실행하는 것 -> 주 1회
     * 이건 시간에 맞춰 작동할 수 있도록...? runtime시? 언제가 좋을 것인가
     */
    @PostMapping("/checkWinner")
    public List<WinnerCommand> checkWinner(@RequestParam("drwNo") String drwNo) {
        log.info("당첨자 선별 후 저장하기");
        List<WinnerCommand> winnerList = winningService.saveWinner(drwNo);
        return winnerList;
    }

    /**
     * 사용자가 '요청한 회차' 결과 리스트
     *
     * @param drwNo
     */
    @GetMapping("/result/winner")
    public String requestGameResult(@RequestParam("drwNo") String drwNo, Model model) {
        log.info("당첨자 요청 페이지 controller");
        //회차 리스트
        List<Long> cycleNumList = winningService.getCycleNumList();
        //당첨자 리스트 가져오기

        //map에 담아서 회차 리스트랑 같이 보내기 -> 이 경우 리스트를 계속 요청하게 되는데 다른 방법이 있나요
        Map<String, Object> resultWinnerMap = new HashMap<>();
        resultWinnerMap.put("cycleNumList", cycleNumList);
        model.addAttribute("resultWinnerMap", resultWinnerMap);

        return "";
    }



}
