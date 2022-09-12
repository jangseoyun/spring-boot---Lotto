package lotto.practice.random.logging;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.common.CommonPointCut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
public class LoggingAspect extends CommonPointCut {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Around("logging()")//joinPoint TODO 버그 찾아보기 , before after 등 찾아보기
    public Object logging(ProceedingJoinPoint joinPoint) {//실제로 실행되는 타켓
        Object result;
        try {//weaving
            startTime.set(System.nanoTime()); //부가적인 기능

            result = joinPoint.proceed(); //타겟 실행  - 관심사

            var localStartTime = startTime.get() != null ? startTime.get() : 0L;
            var elapsedTime = System.nanoTime() - localStartTime;
            var elapsedTimeByMs = TimeUnit.MILLISECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

            log.info("{} start. time {}", joinPoint.getSignature().getName(), elapsedTimeByMs);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
