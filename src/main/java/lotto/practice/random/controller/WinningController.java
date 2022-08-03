package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.winning.WinningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WinningController {

    private final WinningService winningService;

    @GetMapping("/request/winner")
    public void thisWeekWinner(@RequestParam("drwNo") Long drwNo) {
        log.info("이번회차 당첨자 요청 controller");
        //TODO: return DTO로 변경해서 화면으로 보내기!
        winningService.getWinner(drwNo);
    }

}
