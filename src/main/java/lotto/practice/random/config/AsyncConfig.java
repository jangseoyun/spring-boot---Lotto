package lotto.practice.random.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration//스프링 부트가 컨피크 파일이라는 것을 인식
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);//큐 용량(대기할 수 있는 사이즈 )
        executor.setThreadNamePrefix("heowc-async-");
        executor.initialize();
        return executor;
    }

}
