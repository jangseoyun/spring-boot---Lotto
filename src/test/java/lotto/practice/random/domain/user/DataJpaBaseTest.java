package lotto.practice.random.domain.user;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@DataJpaTest//영속성 관련된 만들bean 로드됨
@ActiveProfiles("test")
public class DataJpaBaseTest {

    @Autowired
    protected EntityManager entityManager;    //영속성 관련 bean이 때문에 의존성 주입 가능

    @BeforeAll
    static void init() {
        System.out.println("테스트를 시작합니다.");
    }

    @AfterAll
    static void close() {
        System.out.println("ㅏ.디ㄴ됩료종가 트스테든 모");
    }
}
