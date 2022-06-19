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
@Entity(name = "Subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Subscription_no;
    private int user_no;
    private boolean licence;

    @Temporal(TemporalType.TIMESTAMP)
    private Date Subscription_date;



}
