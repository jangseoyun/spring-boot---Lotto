package lotto.practice.random.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
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

    @Email(message = "email 형태만 입력 가능합니다.")
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "join_date")
    private String joinDate;

    //--생성 로직--//
    @Builder(access = AccessLevel.PROTECTED)
    public User(String userId, String userPw, String userEmail, String userTel) {
        setUserId(userId);
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userTel = userTel;
    }

    @PrePersist
    public void joinDate() {
        this.joinDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    //아이디가 3글자 이상 제한
    private void setUserId(String userId){
        if(userId.length() <= 3){
            throw new IllegalArgumentException("아이디는 4글자 이상이어야합니다");
        }
        this.userId = userId;
    }

}
