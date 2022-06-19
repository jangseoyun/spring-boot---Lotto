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
@Entity(name = "ball_storage")
public class BallStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storage_no;

    private int user_no;
    private String six_ball;
    private int storage_cycle;

    @Temporal(TemporalType.TIMESTAMP)
    private Date storage_date;

}
