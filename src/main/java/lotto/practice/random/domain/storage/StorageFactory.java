package lotto.practice.random.domain.storage;

import lotto.practice.random.domain.user.User;

public class StorageFactory {

    public static BallStorage createBallStorage(User findUserOne, String SixBall, Long storageCycle) {
        return getBallStorage(findUserOne, SixBall, storageCycle);
    }

    //일급 컬렉션 컬렉션 객체화
    public static BallStorage getBallStorage(User findUserOne, String sixBall, Long storageCycle) {
        return BallStorage.builder()
                .cycleStorageNum(storageCycle)
                .user(findUserOne)
                .sixBall(sixBall)
                .build();
    }


}
