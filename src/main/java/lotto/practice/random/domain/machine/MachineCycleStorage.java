package lotto.practice.random.domain.machine;


import lombok.*;
import lotto.practice.random.domain.lotto.SixBall;
import lotto.practice.random.domain.machine.converter.BallConverter;
import lotto.practice.random.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Convert(converter = BallConverter.class)
    @Column(name = "ball_01")
    private Ball ball1;

    @Convert(converter = BallConverter.class)
    @Column(name = "ball_02")
    private Ball ball2;

    @Convert(converter = BallConverter.class)
    @Column(name = "ball_03")
    private Ball ball3;

    @Convert(converter = BallConverter.class)
    @Column(name = "ball_04")
    private Ball ball4;

    @Convert(converter = BallConverter.class)
    @Column(name = "ball_05")
    private Ball ball5;

    @Convert(converter = BallConverter.class)
    @Column(name = "ball_06")
    private Ball ball6;

    @Column(name = "six_ball")
    private String sixBall;

    @Convert(converter = BallConverter.class)
    @Column(name = "bonus_ball")
    private Ball bonusBall;

    @Column(name = "storage_cycle")
    private Long storageCycle;

    @Column(name = "cycle_storage_date")
    private String storageDate;

    @Builder(access = AccessLevel.PROTECTED)
    public MachineCycleStorage(User user, SixBall sixBall, Ball bonusBall, Long storageCycle, LocalDateTime storageDate) {
        this.user = user;
        this.ball1 = sixBall.getNo1();
        this.ball2 = sixBall.getNo2();
        this.ball3 = sixBall.getNo3();
        this.ball4 = sixBall.getNo4();
        this.ball5 = sixBall.getNo5();
        this.ball6 = sixBall.getNo6();
        this.sixBall = sixBall.toString();
        this.bonusBall = bonusBall;
        this.storageCycle = storageCycle;
        this.storageDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }


}
