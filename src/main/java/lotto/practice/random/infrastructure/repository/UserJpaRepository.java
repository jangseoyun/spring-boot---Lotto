package lotto.practice.random.infrastructure.repository;

import lotto.practice.random.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
