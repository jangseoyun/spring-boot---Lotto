package lotto.practice.random.controller;

import lotto.practice.random.domain.Pay;
import lotto.practice.random.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LottoController {

    //필드
    //final class => 보안상의 이유로 사용.
    private final static int PRICE = 1000;
    private final LottoService lottoService;

    //생성자
    @Autowired
    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    //메소드
    //메인화면
    @GetMapping("/index")
    public String main(){
        System.out.println("컨트롤러 메인 접속");
        return "lotto-index";
    }

    @GetMapping("/requestBall")
    public String  requestBall(@ModelAttribute Pay pay, Model model){

        System.out.println("컨트롤러 requestBall");

        if(pay.getBuying() == 0 || (pay.getBuying()%PRICE) != 0){
            String warning = "warning";
            model.addAttribute("warning",warning);
        }else{
            //번호 추출 타입 확인
            String resultType = lottoService.buyNum(pay.getType());
            System.out.println("resultType = " + resultType);
        }

        return "redirect:/index";
    }

    /*@PostMapping("/requestBall")
    public String requestBall(){

        return "";
    }*/





}
