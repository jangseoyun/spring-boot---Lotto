package lotto.practice.random.domain.machine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lotto.SixBall;
import lotto.practice.random.domain.machine.dto.LottoCommand;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.infrastructure.repository.CycleStorageJpaRepository;
import lotto.practice.random.infrastructure.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


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
    private final UserJpaRepository userJpaRepository;

    List<HashSet<Integer>> result = new ArrayList<>();

    /**
     * 타입에 따른 6개의 추출 번호
     * * 전체 자동
     * * 반자동 (수동 + 자동)
     * * 전체 수동
     */
    //메소드
    public int operateMachine(LottoCommand command) {

        log.info("service operateMachine 접속");
        log.debug("command = " + command);

        //로그인한 user가져오기
        Optional<User> findUser = userJpaRepository.findById(command.getUserNo());

        //전체 자동
        if (command.getType().equals("allAuto")) {
            log.info("allAuto");
            List<SixBall> allAutoResult = machine.allAutoSixBall(command.getCount());// 6개
            int bonusBall = machine.bonusBall(); //보너스 번호
            //입력
            for (SixBall sixBall : allAutoResult) {
                MachineCycleStorage cycleStorage = MachineFactory.createStorage(command.getStorageCycle(), sixBall, bonusBall, findUser.get());
                csJpaRepository.save(cycleStorage);
            }


        }

        if (command.getType().equals("selectNum")) {
            //반자동
            log.info("selectNum");
            //return machine.selectNumSixBall(command.getBuying(), command.getInputNum());

        }

        if (command.getType().equals("allSelect")) {
            //전체 수동
            log.info("allSelect");
            machine.allSelectSixBall(command.getInputNum());
            //return
        }

        return 0;
    }

}
