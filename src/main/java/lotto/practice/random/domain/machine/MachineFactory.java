package lotto.practice.random.domain.machine;

import lotto.practice.random.domain.lotto.SixBall;
import lotto.practice.random.domain.user.User;

public class MachineFactory {

    public static MachineCycleStorage createStorage(Long cycle, SixBall sixBall, Ball bonusBall, User findUser) {
        //리스트로 변경 set -> list
        //**일급 컬렉션
        //List<Integer> resultList = new ArrayList<>(resultVo);
        return getMachineCycleStorage(cycle, bonusBall, findUser, sixBall);
    }

    private static MachineCycleStorage getMachineCycleStorage(Long cycle, Ball bonusBall, User findUser, SixBall sixBall) {
        MachineCycleStorage buildCycleStorage = MachineCycleStorage.builder()
                .sixBall(sixBall)
                .bonusBall(bonusBall)
                .user(findUser)
                //.sixBall(resultList.get(0)+","+resultList.get(1)+","+resultList.get(2)+","+resultList.get(3)+","+resultList.get(4)+","+resultList.get(5))
                .storageCycle(cycle)
                .build();
        return buildCycleStorage;
    }
    //---
}
