package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.winning.WinningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("winning")
public class WinningController {

    private final WinningService winningService;

    /**
     * 페이지 로딩시 '해당회차' 결과 리스트
     */
    @GetMapping("/result/game")
    public String getCycleNum(HttpSession session) {
        log.info("이번주 회차 번호 + 리스트 가지고 오기");
        winningService.getLastCycleNum();
        return ""; //TODO: 이번주 회차 넣어서 화면으로 보내기
    }

    /**
     * 사용자가 '요청한 회차' 결과 리스트
     *
     * @param drwNo
     */
    //여기서 회차별 로직 확인할 수 있도록
    //game result.do
    @GetMapping("/request/winner")
    public void thisWeekWinner(@RequestParam("drwNo") String drwNo) {
        log.info("이번회차 당첨자 요청 controller");
        //TODO: return DTO로 변경해서 화면으로 보내기
        winningService.getWinner(drwNo);
    }

}
