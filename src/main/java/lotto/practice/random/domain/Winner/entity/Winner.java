package lotto.practice.random.domain.Winner.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.Ball;
import lotto.practice.random.domain.machine.converter.BallConverter;
import lotto.practice.random.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@NotNull
@Table(name = "t_winner")
public class Winner {

    @Id
    @GeneratedValue
    @Column(name = "winner_no")
    private Long no;

    @Column(name = "six_ball")
    private String sixBall; //당첨 번호

    @Convert(converter = BallConverter.class)
    @Column(name = "bonus_ball")
    private Ball bonusBall;

    @Column(name = "win_all_num")
    private String winAllNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user; //당첨자

    @Enumerated(EnumType.STRING)
    @Column(name = "winner_rank")
    private RankType winnerRank;         //당첨 순위

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drw_no")
    private LottoApi lottoApi;   //로또 회차 번호//join

    @Column(name = "win_date")
    private LocalDateTime winDate;  //당첨일

    @Builder
    public Winner(String sixBall, Ball bonusBall, String winAllNum, User user, RankType winnerRank, LottoApi lottoApi) {
        this.sixBall = sixBall;
        this.bonusBall = bonusBall;
        this.winAllNum = winAllNum;
        this.user = user;
        this.winnerRank = winnerRank;
        this.lottoApi = lottoApi;
    }
}
