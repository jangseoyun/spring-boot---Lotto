package lotto.practice.random.infrastructure.repository;

import lotto.practice.random.domain.storage.BallStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageJpaRepository extends JpaRepository<BallStorage, Long> {
}
