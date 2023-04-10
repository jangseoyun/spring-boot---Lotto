package lotto.practice.random.common;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {//해당 표현식 명시자

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    protected void restController() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    protected void postMapping() {//해당 메소드를 aspect로 하겠다
    }

    @Pointcut("@annotation(lotto.practice.random.logging.Logging)")
    protected void logging() {
    }
}
