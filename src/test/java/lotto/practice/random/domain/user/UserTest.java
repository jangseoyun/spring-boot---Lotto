package lotto.practice.random.domain.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
class UserTest {

    @DisplayName("회원가입시 아이디 글자수 제한 체크")
    @Test
    void 아이디글자수제한() {
        //exception
        assertThatThrownBy(() -> {
            new User("tes", "pwtest", "test@gmail.com", "010-2222-3333");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasStackTraceContaining("아이디는 4글자 이상이어야합니다");
    }

    @DisplayName("이메일 형태 검증")
    @Test
    void 이메일형태검증() {
        //exception
        assertThatThrownBy(() -> {
            new User("test", "pwtest", "test/gmailcom", "010-2222-3333");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasStackTraceContaining("email 형태만 입력 가능합니다.");
    }

}