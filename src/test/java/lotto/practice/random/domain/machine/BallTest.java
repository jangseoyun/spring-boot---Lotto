package lotto.practice.random.domain.machine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("볼을 테스트 한다")
class BallTest {

    @DisplayName("1~45의 값을 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 45})
    void test1(int value) {
        //when
        Ball ball = new Ball(value);

        //then
        assertThat(ball).isNotNull();
        assertThat(ball.getValue()).isEqualTo(value);
    }

    @DisplayName("1~45 이외의 값은 exception을 떨군다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void test2(int value) {
        //expect
        assertThatThrownBy(() -> new Ball(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasStackTraceContaining("1~45 사이의 숫자만 가능합니다");//에러 메시지까지 검증

    }


}