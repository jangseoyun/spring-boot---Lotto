package lotto.practice.random.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Machine;
import lotto.practice.random.repository.LottoRepository;
import lotto.practice.random.vo.InputVo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j //log 인터페이스
@RequiredArgsConstructor
public class LottoService {

    //필드
    private final LottoRepository lottoRepository;
    private InputVo inputVo;
    Map<String, Object> resultBall = new HashMap<>();
    //domain
    private Machine machine = new Machine();

    List<HashSet<Integer>> allAutoResult = new ArrayList<>();

    //메소드
    //2-1. 추출한 번호 map에 넣기
    public Map<String, Object> ResultBall(InputVo inputVo){

        log.info("service buyNum 접속");
        log.debug("inputVo = " + inputVo);

        //비즈니스 로직 domain에 요청하여 응답 -> map
        //구입한 갯수
        int buyNumResult = machine.buyNum(inputVo.getBuying());
        log.debug("buynum: "+buyNumResult);
        resultBall.put("buyNum",buyNumResult);

        //타입에 따른 6개의 추출 번호
        if(inputVo.getType().equals("allAuto")){
            //전체 자동
            machine.allAutoNumSix(buyNumResult);
            log.debug("allAuto");
            log.debug("machine.allAutoNumSix(buyNumResult)");

        }else if(inputVo.getType().equals("selectNum")){
            //반자동
            log.debug("selectNum");
            machine.selectNumSix(inputVo.getNumInput(),buyNumResult);

        }else if(inputVo.getType().equals("allSelect")){
            //전체 수동
            log.debug("allSelect");
            machine.allSelectNumSix(inputVo.getNumInput(),buyNumResult);
        }

        //보너스 번호

        return resultBall;
    }

}
