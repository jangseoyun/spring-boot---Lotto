package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.MachineService;
import lotto.practice.random.dto.LottoRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String inputVerify(@ModelAttribute LottoRequestDto lottoRequestDto, Model model) {

        //찍어보고 변환
        log.info("controller lottoRequestDto = " + lottoRequestDto);

        //lottoRequestDto service로 넘길때 변환해줘야한다.
        //빌더패던 변경

        machineService.operateMachine(lottoRequestDto.toCommand());
        return "";//TODO: 화면으로 보낼것
    }


}
