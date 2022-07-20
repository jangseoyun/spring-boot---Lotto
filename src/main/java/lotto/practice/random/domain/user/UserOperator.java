package lotto.practice.random.domain.user;

import java.util.Optional;

public interface UserOperator {

    Long saveUser(User user);
    Optional<User> findUserId(String userId);
    String getOldPassword(String userId);

}
