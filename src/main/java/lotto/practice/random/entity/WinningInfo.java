package lotto.practice.random.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "winning_info")
public class WinningInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_no;
    private int win_cycle_no;
    private int win_ranking;
    private long win_prize;

    @Temporal(TemporalType.TIMESTAMP)
    private Date win_date;

    private String win_sixnum;

}
