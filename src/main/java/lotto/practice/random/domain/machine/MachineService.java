package lotto.practice.random.domain.machine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.Machine;
import lotto.practice.random.dto.InputDto;
import lotto.practice.random.infrastructure.repository.CycleStorageJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MachineService {

    //필드
    private final Machine machine;

    @Qualifier("MachineRepository")
    private final MachineRepository machineRepository;
    private final CycleStorageJpaRepository csJpaRepository;

    List<HashSet<Integer>> result = new ArrayList<>();

    /**
     * 타입에 따른 6개의 추출 번호
     *  * 전체 자동
     *  * 반자동 (수동 + 자동)
     *  * 전체 수동
     */
    //메소드
    public int operateMachine(InputDto inputDto){

        log.info("service operateMachine 접속");
        log.debug("inputDto = " + inputDto);

        //로그인한 user가져오기

        //전체 자동
        if(inputDto.getType().equals("allAuto")){
            log.info("allAuto");
            List<HashSet<Integer>> allAutoResult = machine.allAutoSixBall(inputDto.getBuying());// 6개
            int bonusBall = machine.bonusBall(); //보너스 번호
            //입력
            MachineCycleStorage cycleStorage = MachineFactory.createStorage(inputDto, allAutoResult, bonusBall);
            csJpaRepository.save(cycleStorage);
        }

        if(inputDto.getType().equals("selectNum")){
            //반자동
            log.info("selectNum");
            //return machine.selectNumSixBall(inputDto.getBuying(), inputDto.getInputNum());

        }

        if(inputDto.getType().equals("allSelect")){
            //전체 수동
            log.info("allSelect");
            machine.allSelectSixBall(inputDto.getInputNum());
            //return
        }

        return 0;
    }

}
