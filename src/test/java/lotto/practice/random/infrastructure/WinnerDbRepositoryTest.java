package lotto.practice.random.infrastructure;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.user.DataJpaBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("WinnerRepository.class 테스트")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WinnerDbRepositoryTest extends DataJpaBaseTest {

    private WinnerDbRepository winnerDbRepository;

    @BeforeEach
    void setUp() {
        winnerDbRepository = new WinnerDbRepository(entityManager);
    }

    @Test
    @DisplayName("요청 회차 DB 동행복권 API에서 추첨번호 확인.")
    void 요청회차API가져오기() {
        //given
        Long drwNo = 1026L;

        //when
        LottoApi result = winnerDbRepository.getThisWeekWinning(drwNo);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getDrwNo()).isEqualTo(1026L);

    }

    @DisplayName("회차 전체 리스트 가져오기.")
    @Test
    void 회차전체리스트() {
        //when
        List<Long> resultCycleList = winnerDbRepository.getCycleNumList();

        //then
        assertThat(resultCycleList).isNotNull();
        log.info("전체 회차 번호 : " + resultCycleList);
    }

    @DisplayName("Name")
    @Test
    void Name() {
        //given

        //when

        //then 
        assertThat(false).isTrue();
        //assertThatThrownBy
    }
}