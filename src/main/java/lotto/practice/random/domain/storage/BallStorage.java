package lotto.practice.random.domain.storage;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.machine.MachineCycleStorage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 각 회차별 추출번호 전체 저장소
 */
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
    private MachineCycleStorage cycleStorage;

    @Column(name = "six_ball")
    private String sixBall;

    @Column(name = "storage_cycle_num")
    private String storageCycleNum;

    @Column(name = "ball_storage_date")
    private LocalDateTime storageDate;

}
