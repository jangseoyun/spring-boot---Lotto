package lotto.practice.random.domain.machine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("볼을 테스트 한다")
class BallTest {

    @RepeatedTest(5000)
    @DisplayName("랜덤으로 볼을 생성할 수 있다")
    void 랜덤볼생성() {
        //when
        Ball ball = new Ball();
        //then
        assertThat(ball).isNotNull();
    }

    @Test
    @DisplayName("볼 객체는 equals를 보장한다")
//EqualsAndHashCode
    void 객체Equals() {
        //then
        assertThat(new Ball(1)).isEqualTo(new Ball(1));
    }

    @DisplayName("1~45의 값을 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 45})
    void 볼범위생성(int value) {
        //when
        Ball ball = new Ball(value);

        //then
        assertThat(ball).isNotNull();
        assertThat(ball.getValue()).isEqualTo(value);
    }

    @DisplayName("1~45 이외의 값은 exception을 떨군다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void 볼범위예외(int value) {
        //expect
        assertThatThrownBy(() -> new Ball(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasStackTraceContaining("1~45 사이의 숫자만 가능합니다");//에러 메시지까지 검증

    }


}