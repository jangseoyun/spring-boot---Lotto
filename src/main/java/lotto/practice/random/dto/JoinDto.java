package lotto.practice.random.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JoinDto {

    @NotEmpty(message = "아이디를 입력해 주세요")
    private String userId;

    @NotEmpty(message = "비밀번호를 입력해 주세요")
    private String userPw;

    @NotEmpty(message = "이메일을 입력해 주세요")
    private String userEmail;

    private String userTel;

}
