package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.MachineRepository;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.infrastructure.repository.CycleStorageJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
@Qualifier("MachineRepository")
public class MachineDbRepository implements MachineRepository {

    private final EntityManager em;
    private final CycleStorageJpaRepository csJpaRepository;

    @Override
    public Long saveStorage(MachineCycleStorage cycleStorage) {
        return csJpaRepository.save(cycleStorage).getNo();
    }

}
