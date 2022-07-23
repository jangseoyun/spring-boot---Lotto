package lotto.practice.random.domain.machine;


import lombok.*;
import lotto.practice.random.domain.lotto.SixBall;
import lotto.practice.random.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 각 회차별 사용자 추출 번호 저장소
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@NotNull
@Table(name = "t_cycle_storage")
public class MachineCycleStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cycle_storage_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "ball_01")
    private Ball drwtNo1;

    @Column(name = "ball_02")
    private Ball drwtNo2;

    @Column(name = "ball_03")
    private Ball drwtNo3;

    @Column(name = "ball_04")
    private Ball drwtNo4;

    @Column(name = "ball_05")
    private Ball drwtNo5;

    @Column(name = "ball_06")
    private Ball drwtNo6;

    @Column(name = "six_ball")
    private String sixBall;

    @Column(name = "bonus_ball")
    private int bonusBall;

    @Column(name = "storage_cycle")
    private Long storageCycle;

    @Column(name = "cycle_storage_date")
    private LocalDateTime storageDate;

    @Builder(access = AccessLevel.PROTECTED)
    public MachineCycleStorage(User user, SixBall sixBall,
                               int bonusBall, Long storageCycle) {
        this.user = user;
        //TODO: 컨버터
        this.drwtNo1 = sixBall.getNo1();
        this.drwtNo2 = sixBall.getNo2();
        this.drwtNo3 = sixBall.getNo3();
        this.drwtNo4 = sixBall.getNo4();
        this.drwtNo5 = sixBall.getNo5();
        this.drwtNo6 = sixBall.getNo6();
        this.sixBall = sixBall.toString();
        this.bonusBall = bonusBall;
        this.storageCycle = storageCycle;
    }
}
