package lotto.practice.random.domain.user;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@DataJpaTest//영속성 관련된 만들bean 로드됨
@ActiveProfiles("test")
public class DataJpaBaseTest {

    @PersistenceContext
    protected EntityManager entityManager;    //영속성 관련 bean이 때문에 의존성 주입 가능

    @BeforeAll
    static void init() {
        System.out.println("테스트를 시작합니다.");
    }

    @AfterAll
    static void close() {
        System.out.println("모든 테스트가 종료됩니다.");
    }
}
