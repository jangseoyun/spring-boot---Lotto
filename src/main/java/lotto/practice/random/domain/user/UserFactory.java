package lotto.practice.random.domain.user;

import lotto.practice.random.dto.JoinDto;

public class UserFactory {

    public static User create(JoinDto joinDto){
        User user = User.builder()
                .userId(joinDto.getUserId())
                .userPw(joinDto.getUserPw())
                .userEmail(joinDto.getUserEmail())
                .userTel(joinDto.getUserTel())
                .build();

        return user;
    }
}
