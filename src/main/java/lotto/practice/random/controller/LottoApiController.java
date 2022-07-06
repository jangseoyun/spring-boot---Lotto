package lotto.practice.random.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.service.LottoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Time: 03:41
 * Title: Lotto API
 * Desc: gson라이브러리를 통한 파싱과 데이터 루프
 *
 * == Modification Infomation ==
 * <p>
 * 수정일         수정자         수정내용
 * ------------ ------------ ----------------------------
 * 2022/07/05   Seoyun           최초생성
 *
 * @Version 11
 * @author  Seoyun Jang
 * @since   2022-07-05
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
