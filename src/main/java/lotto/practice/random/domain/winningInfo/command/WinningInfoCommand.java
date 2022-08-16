package lotto.practice.random.domain.winningInfo.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.machine.Ball;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WinningInfoCommand {

    /**
     * - 회차 번호
     * - 당첨번호 6자리
     * - 보너스 번호
     * - 순위
     * - 등위별 총 당첨금액
     * - 등위별 총 당첨자
     * - 당첨자가 수령하게될 금액
     * <p>
     * - 당첨자 아이디 * 로 나오도록 -> user
     * - 수동인지 자동인지 응모 타입 (winningStorage 컬럼에 그럼 응모 타입 있어야함) -> winner로 전달받아서 저장
     */

    private Long lottoCycleNum; //회차 번호
    private String sixBall; //당첨 번호
    private Ball bonusBall;
    private RankType winnerRank; //당첨 순위
    private Long rankTotalAmount; //등위별 총 당첨금액
    private Long rankTotalCount; //등위별 총 당첨자
    private Long gameAmount; //당첨자가 수령할 금액
    private String userId;
    //private 응모타입

    @Builder
    public WinningInfoCommand(Long lottoCycleNum, String sixBall, Ball bonusBall, RankType winnerRank,
                              Long rankTotalAmount, Long rankTotalCount, Long gameAmount, String userId) {
        this.lottoCycleNum = lottoCycleNum;
        this.sixBall = sixBall;
        this.bonusBall = bonusBall;
        this.winnerRank = winnerRank;
        this.rankTotalAmount = rankTotalAmount;
        this.rankTotalCount = rankTotalCount;
        this.gameAmount = setGameAmount(rankTotalAmount, rankTotalCount);
        this.userId = userId;
    }

    private Long setGameAmount(Long rankTotalAmount, Long rankTotalCount) {
        return (rankTotalAmount / rankTotalCount);
    }
}
