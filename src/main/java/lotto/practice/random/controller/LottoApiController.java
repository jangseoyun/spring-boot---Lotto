package lotto.practice.random.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.service.LottoApiService;
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

    @GetMapping("/lotto/loop")
    public void getLotto(@RequestParam Long no) {
        lottoApiService.insertLotto(no);
    }

    /*@GetMapping("/lotto/loop")
    public ResponseEntity<DTO> getLotto(@RequestParam Long no) {
        DTO dto = lottoApiService.insertLotto(no);
        return ResponseEntity.ok().body(dto);
    }*/

}
