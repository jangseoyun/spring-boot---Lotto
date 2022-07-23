package lotto.practice.random.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class SixBallTest {

    @RepeatedTest(1000)
    @DisplayName("여섯개의 볼을 보장한다")
    void name() {
        //when
        SixBall sixBall = new SixBall();

        //then
        assertThat(sixBall.size()).isEqualTo(6);

    }
}