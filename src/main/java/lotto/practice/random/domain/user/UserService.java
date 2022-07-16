package lotto.practice.random.domain.user;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.exception.AlreadyExistIdException;
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
            //User user = new UserFactory().createUser(joinDto);
            User user = UserFactory.create(joinDto);

            return userRepository.saveUser(user);
        }

    }

    public Long joinUser2(JoinDto joinDto){
        //아이디/이메일 검증
        Optional<User> resultUser = userRepository.findUserId(joinDto.getUserId());


        //검색이 되었다면 이미 회원이 존재하는 것 따라서,
        if(!resultUser.isEmpty()){
            //EXCEPTION 중복 회원이면 예외처리
            throw new AlreadyExistIdException("이미 존재하는 아이디 입니다.");
        }
        User user = UserFactory.create(joinDto);
        return userRepository.saveUser(user);

    }

    //userRepository.getOldPassword(joinDto.getUserId());
    public Long joinUser3(JoinDto joinDto){
        //아이디/이메일 검증
        Optional<User> optionalUser = userRepository.findUserId(joinDto.getUserId());
        //optionalUser.ifPresent
        //optionalUser.orElseThrow() **

        //검색이 되었다면 이미 회원이 존재하는 것 따라서,
        if(optionalUser.isEmpty()) {
            User user = UserFactory.create(joinDto);
            return userRepository.saveUser(user);
        }

        throw new AlreadyExistIdException("이미 존재하는 아이디 입니다.");

    }


    //로그인

    //로그아웃

}
