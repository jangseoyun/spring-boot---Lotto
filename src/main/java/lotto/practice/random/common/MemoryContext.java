package lotto.practice.random.common;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.Winner.WinnerService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;

@Component
@RequiredArgsConstructor
public class MemoryContext {
    //TODO
    public static Map<MemoryKey, Object> memory;

    private final WinnerService winnerService;

    @PostConstruct
    public void init() {
        //마지막 회차 번호 얻기
        Long lastCycleNum = winnerService.getLastCycleNum();

        memory.put(LAST_CYCLE_NUM, lastCycleNum);
    }

    @RequiredArgsConstructor
    public enum MemoryKey {
        LAST_CYCLE_NUM("lastCycleNum");

        private final String name;
    }
}
