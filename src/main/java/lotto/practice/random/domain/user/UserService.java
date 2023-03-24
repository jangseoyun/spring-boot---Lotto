package lotto.practice.random.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.dto.LoginDto;
import lotto.practice.random.exception.AlreadyExistIdException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserOperator userOperator;

    //회원가입
    //userRepository.getOldPassword(joinDto.getUserId());
    public Long joinUser(JoinDto joinDto) {
        //아이디/이메일 검증
        Optional<User> optionalUser = userOperator.findUserNo(joinDto.getUserId());

        //검색이 되었다면 이미 회원이 존재하는 것 따라서,
        if (optionalUser.isEmpty()) {
            User user = UserFactory.create(joinDto);
            return userOperator.saveUser(user);
        }

        /*optionalUser.ifPresent(user -> {
            User joinUser = UserFactory.create(joinDto);
            userOperator.saveUser(joinUser);
        });*/

        throw new AlreadyExistIdException("이미 존재하는 아이디 입니다.");

    }

    public Long joinUser2(JoinDto joinDto){
        //아이디/이메일 검증
        Optional<User> resultUser = userOperator.findUserNo(joinDto.getUserId());

        //검색이 되었다면 이미 회원이 존재하는 것 따라서,
        if(!resultUser.isEmpty()){
            //EXCEPTION 중복 회원이면 예외처리
            throw new AlreadyExistIdException("이미 존재하는 아이디 입니다.");
        }
        User user = UserFactory.create(joinDto);
        return userOperator.saveUser(user);

    }

    //로그인
    public User login(LoginDto userLogin) {
        User loginResult = userOperator.checkLogin(userLogin);
        return loginResult;
    }

    //getUserOne
    public User getUserOne(Long userNo) {
        return userOperator.getUserOne(userNo);
    }

}
