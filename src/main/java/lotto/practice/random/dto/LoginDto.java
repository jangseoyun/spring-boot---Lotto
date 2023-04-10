package lotto.practice.random.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDto {
    private String userId;
    private String userPw;

    public LoginDto(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

}
