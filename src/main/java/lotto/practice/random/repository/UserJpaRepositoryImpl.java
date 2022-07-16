package lotto.practice.random.repository;

import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepositoryImpl extends UserRepository, JpaRepository<User, Long> {
}
