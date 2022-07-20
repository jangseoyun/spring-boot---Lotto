package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.user.UserOperator;
import lotto.practice.random.infrastructure.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Qualifier("UserJpaRepositoryImpl")
@RequiredArgsConstructor
@Component
public class UserSpringJpaOperator implements UserOperator {

    private final UserJpaRepository userJpaRepository;
    //JpaRepository<User, Long>

    @Override
    public Long saveUser(User user) {
        return userJpaRepository.save(user).getNo();
    }

    @Override
    public Optional<User> findUserId(String userId) {
        return userJpaRepository.findById();
    }

    @Override
    public String getOldPassword(String userId) {
        return null;
    }
}
