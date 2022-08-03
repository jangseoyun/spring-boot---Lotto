package lotto.practice.random.domain.winning.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Data
@NoArgsConstructor
public class WinningCountMap {

    private Map<String, Integer> winningTotalCount;

    public WinningCountMap(Map<String, Integer> winningTotalCount) {
        this.winningTotalCount = winningTotalCount;
    }

}
