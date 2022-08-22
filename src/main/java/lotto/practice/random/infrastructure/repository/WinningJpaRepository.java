package lotto.practice.random.infrastructure.repository;

import lotto.practice.random.domain.winner.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinningJpaRepository extends JpaRepository<Winner, Long> {

}
