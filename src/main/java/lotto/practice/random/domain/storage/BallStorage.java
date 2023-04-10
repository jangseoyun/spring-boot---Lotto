package lotto.practice.random.domain.storage;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotto.practice.random.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 각 회차별 추출번호 전체 저장소
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@NotNull
@Table(name = "t_ball_storage")
public class BallStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ball_storage_no")
    private Long no;

    private Long cycleStorageNum;//회차번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "six_ball")
    private String sixBall;

    @Column(name = "ball_storage_date")
    private String storageDate;

    @Builder(access = AccessLevel.PROTECTED)
    public BallStorage(Long no, Long cycleStorageNum, User user, String sixBall, LocalDateTime storageDate) {
        this.no = no;
        this.cycleStorageNum = cycleStorageNum;
        this.user = user;
        this.sixBall = sixBall;
        this.storageDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}
