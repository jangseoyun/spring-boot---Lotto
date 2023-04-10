package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApiService;
import lotto.practice.random.dto.LottoApiDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;
import static lotto.practice.random.common.MemoryContext.memory;

/**
 * Created by IntelliJ IDEA.
 * Time: 03:41
 * Title: Lotto Machine
 * Desc: 자동 로또 번호 추출기, 동행복권 API 사용
 * <p>
 * == Modification Information ==
 * <p>
 * 수정일         수정자         수정내용
 * ------------ ------------ ----------------------------
 * 2022/07/05   Seoyun           최초생성
 * 2022/08/26   Seoyun           완성
 *
 * @Version 11
 * @author Seoyun Jang
 * @since 2022-07-05
 */

/**
 * 1. 메인접속
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lottery")
public class MainController {

    private final LottoApiService lottoApiService;

    //메인화면
    @GetMapping("/index")
    public String main(HttpSession session, Model model) {
        log.info("컨트롤러 메인 접속");

        session.setAttribute("lastCycleNum", memory.get(LAST_CYCLE_NUM));
        LottoApiDto lottoResult = lottoApiService.getLottoResult(memory.get(LAST_CYCLE_NUM));
        model.addAttribute("lottoResult", lottoResult);

        return "index/lotto-index";
    }

}
