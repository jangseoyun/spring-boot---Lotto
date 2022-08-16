package lotto.practice.random.common;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.Winner.WinnerService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;

@Component
@RequiredArgsConstructor
public class MemoryContext {
    //TODO
    public static Map<MemoryKey, Object> memory = new HashMap<>();
    private final WinnerService winnerService;
    private final Environment environment;

    /*public MemoryContext(WinnerService winnerService, Environment environment) {
        this.winnerService = winnerService;
        this.environment = environment;
    }*/

    @PostConstruct
    public void init() {
        //마지막 회차 번호 얻기
        Long lastCycleNum = isTestProperties() ? 1L : winnerService.getLastCycleNum();
        memory.put(LAST_CYCLE_NUM, lastCycleNum);
    }

    @RequiredArgsConstructor
    public enum MemoryKey {
        LAST_CYCLE_NUM("lastCycleNum");
        private final String name;
    }

    private boolean isTestProperties() {
        String activeProfile = environment.getActiveProfiles()[0];
        return activeProfile.equals("local") || activeProfile.equals("test");
    }
}
