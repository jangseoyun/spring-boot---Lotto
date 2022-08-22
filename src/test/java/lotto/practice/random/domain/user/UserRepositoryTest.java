package lotto.practice.random.domain.user;

import lotto.practice.random.infrastructure.UserJpaOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends DataJpaBaseTest {
    private UserJpaOperator userJpaOperator; //영속성 관련 bean이 아니기 때문에 의존성 주입 불가능

    @BeforeEach
    void setUp() {
        userJpaOperator = new UserJpaOperator(entityManager);
    }

    @Test
    @DisplayName("회원가입 저장 여부 확인")
    void 회원가입저장() {
        //given
        User user = new User("testId", "testPw", "test@gmail.com", "010-2222-3333");

        //when
        Long joinUser = userJpaOperator.saveUser(user);

        //then
        assertThat(joinUser).isNotNull();
    }
}