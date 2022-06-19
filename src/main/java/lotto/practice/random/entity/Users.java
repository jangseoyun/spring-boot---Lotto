package lotto.practice.random.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Slf4j // log
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")//entity+table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동생성 db에서
    @Column(name = "user_no")
    private int userNo;

    private String user_id;
    private String user_pw;
    private String user_email;
    private String user_phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date join_date;

}
