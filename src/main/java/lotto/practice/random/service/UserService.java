package lotto.practice.random.service;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.entity.User;
import lotto.practice.random.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public Long joinUser(JoinDto joinDto){
        //아이디/이메일 검증

        User user = User.builder()
                .userId(joinDto.getUserId())
                .userPw(joinDto.getUserPw())
                .userEmail(joinDto.getUserEmail())
                .userTel(joinDto.getUserTel())
                .build();

        return userRepository.saveUser(user);
    }



    //로그인

    //로그아웃

}
