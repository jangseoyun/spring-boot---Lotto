package lotto.practice.random.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. 동행복권 API (데이터 루프 저장)
 * gson 사용
 */
@RestController
@AllArgsConstructor
@Slf4j
public class LottoApiController {
    private final LottoApiService lottoApiService;

    /**
     * 1~요청회차 api loop 저장
     */
    @GetMapping("/lotto/loop")
    public void lottoLoop(@RequestParam Long no) {
        lottoApiService.insertLotto(no);
    }

    /**
     * 단일 회차 저장
     */
    @GetMapping("/lotto/cycle-num")
    public void getLotto(@RequestParam Long no) {
        lottoApiService.insertOne(no);
    }

    /**
     * 마지막 회차 번호 요청
     */
    /*@GetMapping("/lotto/last-cycle")
    public Long getLastCycle() {
        Long lastCycleNum = winnerService.getLastCycleNum();
        memory.put(LAST_CYCLE_NUM, lastCycleNum);
        return lastCycleNum;
    }*/

}
