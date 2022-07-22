package lotto.practice.random.infrastructure.repository;

import lotto.practice.random.domain.machine.MachineCycleStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleStorageJpaRepository extends JpaRepository<MachineCycleStorage, Long> {
}
