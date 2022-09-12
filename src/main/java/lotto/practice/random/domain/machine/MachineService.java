package lotto.practice.random.domain.machine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lotto.SixBall;
import lotto.practice.random.domain.machine.command.LottoCommand;
import lotto.practice.random.domain.machine.command.Lottotype;
import lotto.practice.random.domain.storage.BallStorage;
import lotto.practice.random.domain.storage.StorageFactory;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.infrastructure.repository.CycleStorageJpaRepository;
import lotto.practice.random.infrastructure.repository.StorageJpaRepository;
import lotto.practice.random.infrastructure.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final StorageJpaRepository storageJpaRepository;

    /**
     * 타입에 따른 6개의 추출 번호
     * * 전체 자동
     * * 반자동
     * * 전체 수동
     */
    //메소드
    public List<MachineCycleStorage> operateMachine(LottoCommand command) {
        log.info("service operateMachine 접속");
        log.debug("command = " + command);

        //1.번호 추출 요청한 user 가져오기
        Optional<User> findUserOne = userJpaRepository.findById(command.getUserNo());

        //2.번호 추출 및 등록 요청
        List<MachineCycleStorage> cycleStorageList = new ArrayList<>();

        //2-1.전체 자동
        if (command.getLottotype() == Lottotype.ALLAUTO) {
            log.info("allAuto");
            List<SixBall> sixBallList = machine.allAutoSixBall(command.getCount());// 6개
            //보너스 번호 -> Ball.class에서 호출(new Ball())
            saveStorage(command, findUserOne, cycleStorageList, sixBallList);
        }

        //2-2.반자동/전체수동
        if (command.getLottotype() == Lottotype.SELECTNUM || command.getLottotype() == Lottotype.ALLSELECT) {
            log.info("selectNum");
            List<SixBall> sixBallList = machine.selectNumSixBall(command.getCount(), command.getInputNum());//받은 번호, 구입 티켓 수 return : 6개의 볼
            saveStorage(command, findUserOne, cycleStorageList, sixBallList);
        }

        return cycleStorageList;
    }

    private void saveStorage(LottoCommand command, Optional<User> findUserOne, List<MachineCycleStorage> cycleStorageList, List<SixBall> sixBallList) {
        for (SixBall sixBall : sixBallList) {
            MachineCycleStorage storageVo = MachineFactory.createStorage(command.getStorageCycle(), sixBall, new Ball(), findUserOne.get());
            csJpaRepository.save(storageVo);
            cycleStorageList.add(storageVo);
            //전체 저장소 저장
            savaBallStorage(storageVo, findUserOne);
        }
    }

    /**
     * BallStorage에 저장
     */
    private Long savaBallStorage(MachineCycleStorage storageVo, Optional<User> findUserOne) {//회차번호, sixBall, user
        BallStorage ballStorage = StorageFactory.createBallStorage(findUserOne.get(), storageVo.getSixBall(), storageVo.getStorageCycle());
        BallStorage ballStorageNo = storageJpaRepository.save(ballStorage);
        log.info("BallStorageNo 저장 getNo = " + ballStorageNo.getNo());
        return ballStorageNo.getNo();
    }

}
