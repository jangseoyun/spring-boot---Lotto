package lotto.practice.random.domain.winningInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.winningAmount.RankAmountCommand;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WinningResult {
    private RankAmountCommand rankAmountCommand;
    private TotalCount totalCount;

    public WinningResult(RankAmountCommand rankAmountCommand, TotalCount totalCount) {
        this.rankAmountCommand = rankAmountCommand;
        this.totalCount = totalCount;
    }
}
