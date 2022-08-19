package lotto.practice.random.domain.winner.domain;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
@DisplayName("FindWinnerImplTest.class를 테스트한다.")
class FindWinnerImplTest {
    //user
    //User user = new User("id", "pw", "email@gmail.com", "010-2222-3333");

    //bonusBall
    //Ball ball = new Ball();
    //MachineCycleStorage userStorage = new MachineCycleStorage(user, sixBall, ball, 1026L);

    @DisplayName("사용자 추출번호 순서 보장")
    @Test
    void 사용자추출번호() {//getSixNum() 순서대로 값이 들어가는지 확인, 6이상 exception

    }


    @DisplayName("여섯개의 동행복권 당첨번호와 사용자추출 번호의 일치여부를 확인한다.")
    @Test
    void 당첨번호갯수확인() {//ValidSixBall() //sixnum, lottoapi 6개의 번호

    }

}