package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;
import static lotto.practice.random.common.MemoryContext.memory;

/**
 * Created by IntelliJ IDEA.
 * Time: 03:41
 * Title: Lotto API
 * Desc: 자동 로또 번호 추출기, 동행복권 API 사용
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

/**
 * 1. 메인접속
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lottery")
public class MainController {
    //필드
    private final LottoApiService lottoApiService;

    //메소드
    //메인화면
    @GetMapping("/index")
    public String main(Model model) {
        log.info("컨트롤러 메인 접속");

        model.addAttribute("lastCycleNum", memory.get(LAST_CYCLE_NUM));

        //지난 회차 당첨 번호
        return "index/lotto-index";
    }

    //

}
