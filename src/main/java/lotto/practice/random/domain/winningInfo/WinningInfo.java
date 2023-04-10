package lotto.practice.random.domain.winningInfo;

import lombok.*;
import lotto.practice.random.domain.winner.RankType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@NotNull
@Table(name = "t_winning_info")
public class WinningInfo {

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "winning_no")
    private Long winningNo;

    @Column(name = "lotto_cycle_num")
    private Long lottoCycleNum; //회차 번호

    @Column(name = "six_ball")
    private String sixBall; //당첨 번호

    @Column(name = "bonus_ball")
    private int bonusBall;

    @Enumerated(EnumType.STRING)
    @Column(name = "winner_rank")
    private RankType winnerRank; //당첨 순위

    @Column(name = "rank_total_amount")
    private Long rankTotalAmount; //등위별 총 당첨금액

    @Column(name = "rank_total_count")
    private Long rankTotalCount; //등위별 총 당첨자

    @Column(name = "game_amount")
    private Long gameAmount; //당첨자가 수령할 금액

    @Column(name = "user_id")
    private String userId;

    @Builder
    public WinningInfo(Long lottoCycleNum, String sixBall, int bonusBall, RankType winnerRank,
                       Long rankTotalAmount, Long rankTotalCount, String userId) {
        this.lottoCycleNum = lottoCycleNum;
        this.sixBall = sixBall;
        this.bonusBall = bonusBall;
        this.winnerRank = winnerRank;
        this.rankTotalAmount = rankTotalAmount;
        this.rankTotalCount = rankTotalCount;
        this.gameAmount = setGameAmount(rankTotalAmount, rankTotalCount);
        this.userId = userId;
    }

    //사용자가 수령하게될 금액
    private Long setGameAmount(Long rankTotalAmount, Long rankTotalCount) {
        return (rankTotalAmount / rankTotalCount);
    }

}
