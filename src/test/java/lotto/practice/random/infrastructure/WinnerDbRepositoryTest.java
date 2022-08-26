package lotto.practice.random.infrastructure;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.user.DataJpaBaseTest;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.winner.RankType;
import lotto.practice.random.domain.winner.entity.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
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
        LottoApi getApiResult = winnerDbRepository.getThisWeekWinning(drwNo);

        //then
        assertThat(getApiResult).isNotNull();
        assertThat(getApiResult.getDrwNo()).isEqualTo(1026L);

    }

    @DisplayName("이번주 추첨한 사용자 정보 리스트 가져오기.")
    @Test
    void 이번주추첨전체사용자() {
        //when
        List<MachineCycleStorage> getFindAllUser = winnerDbRepository.findAllUser();

        //then
        assertThat(getFindAllUser).isNotNull();
    }

    @DisplayName("회차 전체 리스트 가져오기.")
    @Test
    void 회차전체리스트() {
        //when
        List<Long> getResultCycleList = winnerDbRepository.getCycleNumList();

        //then
        assertThat(getResultCycleList).isNotNull();
        log.info("전체 회차 번호 : " + getResultCycleList);
    }

    @DisplayName("이번주 당첨자 저장하기") //user 외부에서 생성해서 받아서 사용 : 미리 생성하거나 , 다른 테스트에서 가지고 오거나
    //@Rollback(value = false)
    @Test
    void 이번주당첨자저장하기() {
        //given
        Winner winnerBuild = Winner.builder()
                .sixBall("1,2,3,4,5,6")
                .bonusBall(new Ball(20))
                .winAllNum("1,2,3,4,5,6,20")
                .user(new User("testId", "testPw", "user@gmail.com", "010-222-3333"))
                .winnerRank(RankType.FIFTH)
                .lottoCycleNum(1026L)
                .drwNoDate("2022.08.26")
                .build();

        //when
        Long saveWinner = winnerDbRepository.saveWinner(winnerBuild);
        log.info("saveWinner : " + saveWinner);

        //then
        assertThat(saveWinner).isNotNull();
    }
}