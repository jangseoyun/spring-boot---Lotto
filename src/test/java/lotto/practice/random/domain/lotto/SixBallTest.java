package lotto.practice.random.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SixBall.class를 테스트 한다")
class SixBallTest {

    @RepeatedTest(1000)
    @DisplayName("여섯개의 볼을 보장한다")
    void 여섯개의공추출() {
        //when
        SixBall sixBall = new SixBall();

        //then
        assertThat(sixBall.size()).isEqualTo(6);
        //getnum이 숫자가 같으면 안됨
    }
}