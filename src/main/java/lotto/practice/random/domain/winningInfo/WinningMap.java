package lotto.practice.random.domain.winningInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.winningAmount.RankAmountCommand;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WinningMap {//TODO: 네이밍 변경 result
    private RankAmountCommand rankAmountCommand;
    private TotalCount totalCount;

    public WinningMap(RankAmountCommand rankAmountCommand, TotalCount totalCount) {
        this.rankAmountCommand = rankAmountCommand;
        this.totalCount = totalCount;
    }
}
