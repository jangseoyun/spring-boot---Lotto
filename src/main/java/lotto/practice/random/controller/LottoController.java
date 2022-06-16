package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.vo.InputVo;
import lotto.practice.random.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class LottoController {

    //필드
    //final class? => 보안상의 이유로 사용.
    public final static int PRICE = 1000; //lotto 금액
    private final LottoService lottoService;

    //생성자
    //@Autowired

    //메소드
    //메인화면
    @GetMapping("/index")
    public String main(){
        System.out.println("컨트롤러 메인 접속");
        return "lotto-index";
    }

    @GetMapping("/requestBall")
    public String  requestBall(@ModelAttribute InputVo inputVo, Model model){

        System.out.println("컨트롤러 requestBall");

        //1. 잘못된 입력 확인
        if(inputVo.getBuying() == 0 || (inputVo.getBuying()%PRICE) != 0){
            String warning = "천원 단위로 입력해주세요!";
            model.addAttribute("warning",warning);
            return "redirect:/index";
        }else{
            //2-1. 구입한 갯수/추출 번호 요청 후 map으로 받기
            lottoService.ResultBall(inputVo);
        }

        return "redirect:/index";
    }






}
