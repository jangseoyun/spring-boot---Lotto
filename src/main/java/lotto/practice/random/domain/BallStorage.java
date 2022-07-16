package lotto.practice.random.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@NotNull
@Table(name = "t_ball_storage")
public class BallStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ball_storage_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cycle_storage_no")
    private CycleStorage cycleStorage;

    @Column(name = "six_ball")
    private String sixBall;

    @Column(name = "storage_cycle_num")
    private String storageCycleNum;

    @Column(name = "ball_storage_date")
    private LocalDateTime storageDate;

}
