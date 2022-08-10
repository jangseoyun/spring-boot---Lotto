package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.winning.WinningService;
import lotto.practice.random.domain.winning.command.WinnerCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final WinningService winningService;

    /**
     * 사용자가 '요청한 회차' 결과 리스트
     * @param drwNo
     */
    //여기서 회차별 로직 확인할 수 있도록
    //game result.do
    @GetMapping("/result/winner")
    public String requestGameResult(@RequestParam("drwNo") String drwNo, Model model) {
        log.info("당첨자 요청 페이지 controller");

        //Long lastCycleNum = winningService.getLastCycleNum();//이건 로그인 했을때 넣어야함 -> 로그인 방식은 JWT로
        //session.setAttribute("lastNum", lastCycleNum);//일주일마다 세션이 만료되도록 매주 회차 변경

        //회차 리스트
        List<Long> cycleNumList = winningService.getCycleNumList();

        //이번 회차 당첨자 리스트 가져오기
        //TODO: return DTO로 변경해서 화면으로 보내기
        List<WinnerCommand> winnerList = winningService.saveWinner(drwNo);

        //map에 담아서 회차 리스트랑 같이 보내기 -> 이 경우 리스트를 계속 요청하게 되는데 다른 방법이 있나요
        Map<String, Object> resultWinnerMap = new HashMap<>();
        resultWinnerMap.put("cycleNumList", cycleNumList);
        resultWinnerMap.put("winnerInfo", winnerList);
        model.addAttribute("resultWinnerMap", resultWinnerMap);

        return "";
    }

}
