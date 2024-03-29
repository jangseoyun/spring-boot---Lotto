package lotto.practice.random.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {

    @NotEmpty(message = "아이디를 입력해 주세요")
    private String userId;

    @NotEmpty(message = "비밀번호를 입력해 주세요")
    private String userPw;

    @NotEmpty(message = "이메일을 입력해 주세요")
    private String userEmail;

    private String userTel;
}
