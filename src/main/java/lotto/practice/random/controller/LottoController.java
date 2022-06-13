package lotto.practice.random.controller;

import lotto.practice.random.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LottoController {

    //필드
    //final class => 보안상의 이유로 사용.
    private final LottoService lottoService;

    //생성자
    @Autowired
    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    //메소드
    @GetMapping("index")
    public String main(){

        return "lotto-index";
    }

    //구입
    public String buyLotto(int buying){
        String buyResult = lottoService.buyResult(buying);
        System.out.println("buyResult = " + buyResult);
        return buyResult;
    }



}
