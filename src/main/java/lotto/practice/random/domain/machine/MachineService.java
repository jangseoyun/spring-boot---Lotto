package lotto.practice.random.domain.machine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.Machine;
import lotto.practice.random.dto.InputDto;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class MachineService {

    //필드
    private final Machine machine;

    Map<String, Object> resultBall = new HashMap<>();
    List<HashSet<Integer>> allAutoResult = new ArrayList<>();

    //메소드
    public Map<String, Object> operateMachine(InputDto inputDto){

        log.info("service buyNum 접속");
        log.debug("inputVo = " + inputDto);

        //비즈니스 로직 domain에 요청하여 응답 -> map
        //구입한 갯수
        int buyNumResult = machine.buyNum(inputDto.getBuying());
        log.debug("buynum: "+buyNumResult);
        resultBall.put("buyNum",buyNumResult);

        //타입에 따른 6개의 추출 번호
        if(inputDto.getType().equals("allAuto")){
            //전체 자동
            machine.allAutoNumSix(buyNumResult);
            log.debug("allAuto");
            log.debug("machine.allAutoNumSix(buyNumResult)");

        }else if(inputDto.getType().equals("selectNum")){
            //반자동
            log.debug("selectNum");
            //machine.selectNumSix();

        }else if(inputDto.getType().equals("allSelect")){
            //전체 수동
            log.debug("allSelect");
            //machine.allSelectNumSix();
        }

        //보너스 번호

        return resultBall;
    }

}
