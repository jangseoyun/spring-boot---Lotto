package lotto.practice.random.domain.user;

import lotto.practice.random.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Long saveUser(User user);
    Optional<User> findUserId(String userId);
    String getOldPassword(String userId);

}
