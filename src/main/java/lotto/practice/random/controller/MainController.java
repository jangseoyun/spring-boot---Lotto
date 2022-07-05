package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.service.LottoService;
import lotto.practice.random.vo.InputVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
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

    @GetMapping("/requestBall")
    public String  requestBall(@ModelAttribute InputVo inputVo, Model model){

        log.info("컨트롤러 requestBall");

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
