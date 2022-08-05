package lotto.practice.random.domain.winning.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@NotNull
@Table(name = "t_winning_info")
public class WinningInfo {

    //id전략
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "winning_info_no")
    private Long no;

    @Column(name = "tot_selling_price")
    private Long totSellingPrice;  //총 판매액
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;  //당첨자

    @Column(name = "lotto_cycle_num")
    private String lottoCycleNum;   //로또 회차 번호

    @Column(name = "winner_rank")
    private int winnerRank;         //당첨 순위
    @Column(name = "total_amount")
    private Long totalAmount;       //등위별 총 당첨금액
    @Column(name = "winner_amount")
    private Long winnerAmount;      //1인당 당첨금액

    @Column(name = "winner_total_count")
    private int winnerTotalCount;

    @Column(name = "win_date")
    private LocalDateTime winDate;  //당첨일

    @Column(name = "win_num")
    private String winAllNum;   //당첨 번호 -> 보너스 번호 포함

    @Builder
    public WinningInfo(Long no, Long totSellingPrice, User user, String lottoCycleNum, int winnerRank, Long totalAmount, Long winnerAmount, LocalDateTime winDate, String winAllNum, int winnerTotalCount) {
        this.no = no;
        this.totSellingPrice = totSellingPrice;
        this.user = user;
        this.lottoCycleNum = lottoCycleNum;
        this.winnerRank = winnerRank;

        this.winnerTotalCount = winnerTotalCount;
        this.totalAmount = totalAmount;
        this.winnerAmount = setWinnerAmount(totalAmount, winnerTotalCount);
        this.winDate = winDate;
        this.winAllNum = winAllNum;
    }

    private Long setWinnerAmount(Long totalAmount, int winnerTotalCount) {
        return (totalAmount / winnerTotalCount);
    }


}
