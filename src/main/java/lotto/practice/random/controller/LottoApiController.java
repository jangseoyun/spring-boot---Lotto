package lotto.practice.random.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.lottoapi.LottoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;
import static lotto.practice.random.common.MemoryContext.memory;

/**
 * 1. 동행복권 API (데이터 루프 저장)
 * gson 사용
 */

@RestController
@AllArgsConstructor
@Slf4j
public class LottoApiController {

    private final LottoApiService lottoApiService;
    private final WinnerService winnerService;

    @GetMapping("/lotto/loop")
    public void getLotto(@RequestParam Long no) {
        lottoApiService.insertLotto(no);
    }

    @GetMapping("/lotto/last-cycle")//대문자 사용하지 않는다
    public Long getLastCycle() {
        Long lastCycleNum = winnerService.getLastCycleNum();
        memory.put(LAST_CYCLE_NUM, lastCycleNum);
        return lastCycleNum;
    }

    /*@GetMapping("/lotto/loop")
    public ResponseEntity<DTO> getLotto(@RequestParam Long no) {
        DTO dto = lottoApiService.insertLotto(no);
        return ResponseEntity.ok().body(dto);
    }*/

}
