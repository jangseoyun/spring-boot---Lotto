package lotto.practice.random.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.dto.JoinDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_user",
        uniqueConstraints = {@UniqueConstraint(
            name = "id_email_unique",
            columnNames = {"user_id", "user_email"})}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동생성 db에서
    @Column(name = "user_no")
    private Long no;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "join_date")
    private String joinDate;

    //--생성 로직--//
    @Builder
    public User(String userId, String userPw, String userEmail, String userTel) {
        this.userId = userId;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userTel = userTel;
    }
    @PrePersist
    public void joinDate(){
        this.joinDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}
