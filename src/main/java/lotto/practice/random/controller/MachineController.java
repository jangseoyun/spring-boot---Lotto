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

import javax.validation.Valid;

/**
 * 사용자 랜덤 번호 추출 요청
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/machine")
public class MachineController {

    //필드
    public final MachineService machineService;

    //메서드
    @GetMapping("/lottoNum")
    public String inputVerify(@ModelAttribute @Valid InputDto inputDto, Model model){

        log.info("controller inputDto = " + inputDto);

        machineService.operateMachine(inputDto);
        //2-1. 구입한 갯수/추출 번호 요청 후 map으로 받기

        return "";
    }


}
