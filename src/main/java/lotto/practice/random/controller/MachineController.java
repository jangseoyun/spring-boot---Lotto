package lotto.practice.random.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.machine.MachineService;
import lotto.practice.random.dto.LottoRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        log.info("controller lottoRequestDto = " + lottoRequestDto);

        //lottoRequestDto service로 넘길때 command object로 변환해줘야한다.
        List<MachineCycleStorage> numResultList = machineService.operateMachine(lottoRequestDto.toCommand(lottoRequestDto));
        model.addAttribute("NumResultList", numResultList);

        return "lotto-result-num";
    }


}
