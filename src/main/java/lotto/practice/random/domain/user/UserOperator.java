package lotto.practice.random.domain.user;

import lotto.practice.random.dto.LoginDto;

import java.util.Optional;

public interface UserOperator {

    //회원 저장
    Long saveUser(User user);

    Optional<User> findUserNo(String userId);

    User checkLogin(LoginDto loginUser);

    User getUserOne(Long userNo);

}
