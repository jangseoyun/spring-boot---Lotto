package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.service.LottoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public final static int PRICE = 1000; //lotto 금액
    private final LottoService lottoService;

    //메소드
    //메인화면
    @GetMapping("/index")
    public String main(){
        log.info("컨트롤러 메인 접속");
        return "index/lotto-index";
    }

}
