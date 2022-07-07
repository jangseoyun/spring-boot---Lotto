package lotto.practice.random.service;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.entity.User;
import lotto.practice.random.exception.AlreadyExistIdException;
import lotto.practice.random.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public Long joinUser(JoinDto joinDto){
        //아이디/이메일 검증
        Optional<User> resultUser = userRepository.findUserId(joinDto.getUserId());

        //검색이 되었다면 이미 회원이 존재하는 것 따라서,
        if(!resultUser.isEmpty()){
            //EXCEPTION 중복 회원이면 예외처리
            throw new AlreadyExistIdException("이미 존재하는 아이디 입니다.");
        }else {
            //result값이 없으면 가입 진행
            User user = User.builder()
                    .userId(joinDto.getUserId())
                    .userPw(joinDto.getUserPw())
                    .userEmail(joinDto.getUserEmail())
                    .userTel(joinDto.getUserTel())
                    .build();

            return userRepository.saveUser(user);
        }

    }



    //로그인

    //로그아웃

}
