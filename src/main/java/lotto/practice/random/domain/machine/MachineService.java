package lotto.practice.random.domain.machine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lotto.SixBall;
import lotto.practice.random.domain.machine.dto.LottoCommand;
import lotto.practice.random.domain.machine.dto.Lottotype;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.infrastructure.repository.CycleStorageJpaRepository;
import lotto.practice.random.infrastructure.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //List<HashSet<Integer>> result = new ArrayList<>();

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
        if (command.getLottotype() == Lottotype.ALLAUTO) {
            log.info("allAuto");
            List<SixBall> sixBallList = machine.allAutoSixBall(command.getCount());// 6개
            //Ball bonusBall = new Ball(); //보너스 번호
            //입력
            for (SixBall sixBall : sixBallList) {
                MachineCycleStorage cycleStorage = MachineFactory.createStorage(command.getStorageCycle(), sixBall, new Ball(), findUser.get());
                csJpaRepository.save(cycleStorage);
            }


        }

        if (command.getLottotype() == Lottotype.SELECTNUM) {
            //반자동
            log.info("selectNum");
            //return machine.selectNumSixBall(command.getBuying(), command.getInputNum());

        }

        if (command.getLottotype() == Lottotype.ALLSELECT) {
            //전체 수동
            log.info("allSelect");
            machine.allSelectSixBall(command.getInputNum());
            //return
        }

        return 0;
    }

}
