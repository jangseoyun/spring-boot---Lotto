package lotto.practice.random.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "t_user",
        uniqueConstraints = {@UniqueConstraint(
            name = "id_email_unique",
            columnNames = {"user_id", "user_email"})}
)
@NotNull
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동생성 db에서
    @Column(name = "user_no")
    private Long no;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_tel")
    private String userTel;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_date")
    private Date joinDate;

}
