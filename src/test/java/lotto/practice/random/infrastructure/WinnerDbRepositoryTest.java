package lotto.practice.random.infrastructure;

import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.user.DataJpaBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WinnerRepository.class 테스트")
class WinnerDbRepositoryTest extends DataJpaBaseTest {

    private WinnerDbRepository winnerDbRepository;

    @BeforeEach
    void setUp() {
        winnerDbRepository = new WinnerDbRepository(entityManager);
    }

    @DisplayName("요청 회차 DB 동행복권 API에서 추첨번호 확인.")
    @Test
    void 요청회차API가져오기() {
        //given
        Long drwNo = 1026L;

        //when
        LottoApi result = winnerDbRepository.getThisWeekWinning(drwNo);

        //then
        assertThat(result).isNotNull();
        //assertThat(resultWeekWinning.getDrwNo()).isEqualTo(1026L);
        //assertThatThrownBy
    }


}