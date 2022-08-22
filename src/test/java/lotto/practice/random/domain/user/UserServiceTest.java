package lotto.practice.random.domain.user;

import lotto.practice.random.dto.JoinDto;
import lotto.practice.random.exception.AlreadyExistIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

//@ActiveProfiles("test")
@SpringBootTest(classes = UserService.class)
class UserServiceTest {

    @Autowired
    UserService userService;//주입 받아야함!!

    @MockBean
    UserOperator userOperator;

    static JoinDto joinDto;

    @BeforeAll
    static void beforeAll() {
        joinDto = JoinDto.builder()
                .userId("test")
                .userEmail("test@naver.com")
                .userPw("a1s2d3f4g5h6u7")
                .userTel("01012341234")
                .build();
    }

    @Test
    @DisplayName("기존 회원이 없다면 회원가입 가능.")
    void joinUser() {
        //when
        Mockito.when(userOperator.findUserNo(Mockito.any()))
                .thenReturn(Optional.empty());

        userService.joinUser(joinDto);

        //then
        Mockito.verify(userOperator, times(1))
                .saveUser(any());
    }

    @Test
    @DisplayName("가입이력이 있으면 회원가입을 할 수 없다.")
    void 기존회원조회() {
        //when
        Mockito.when(userOperator.findUserNo(Mockito.any()))
                .thenReturn(Optional.of(new User()));

        //expect
        Assertions.assertThatThrownBy(() -> userService.joinUser(joinDto))
                .isInstanceOf(AlreadyExistIdException.class);

        //then
        Mockito.verify(userOperator, times(0)).saveUser(any());
    }

}