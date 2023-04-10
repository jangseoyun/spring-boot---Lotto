package lotto.practice.random.common;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.lottoapi.LottoApiService;
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
    public static Map<MemoryKey, Long> memory = new HashMap<>();
    private final LottoApiService lottoApiService;
    private final Environment environment;

    @PostConstruct //생성자 호출 후 자동으로 실행 lottoApiService 생성 -> init 메서드 실행하여 마지막 회차 번호 가져옴
    public void init() {
        //마지막 회차 번호 얻기
        Long lastCycleNum = isTestProperties() ? 1L : lottoApiService.getLastCycleNum();
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
