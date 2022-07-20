package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.dto.InputDto;
import lotto.practice.random.domain.machine.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/machine")
public class MachineController {

    //필드
    public final static int PRICE = 1000; //lotto 금액
    public final MachineService machineService;

    //메서드
    /**
     * @return 사용자 입력값 검증 및 랜덤 번호 반환
     */
    @GetMapping("/resultLottoNum")
    public String inputVerify(@ModelAttribute InputDto inputDto, Model model){
        //사용자 입력값 검증
        if(inputDto.getBuying() == 0 || (inputDto.getBuying()%PRICE) != 0){
            String warning = "천원 단위로 입력해주세요!";
            model.addAttribute("warning",warning);
            return "redirect:/index";
        }else{
            //2-1. 구입한 갯수/추출 번호 요청 후 map으로 받기

        }

        return "";
    }


}
