package lotto.practice.random.domain.machine;

import lotto.practice.random.domain.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MachineFactory {

    //1. 출력된 공 꺼내기
    //2. 유저 가져오기

    public static MachineCycleStorage createStorage(Long cycle, HashSet<Integer> resultVo, int bonusBall, User findUser) {
        //리스트로 변경 set -> list
        //**TODO: 일급 컬렉션
        List<Integer> resultList = new ArrayList<>(resultVo);
        return getMachineCycleStorage(cycle, bonusBall, findUser, resultList);
    }

    private static MachineCycleStorage getMachineCycleStorage(Long cycle, int bonusBall, User findUser, List<Integer> resultList) {
        MachineCycleStorage buildCycleStorage = MachineCycleStorage.builder()
                .drwtNo1(resultList.get(0))
                .drwtNo2(resultList.get(1))
                .drwtNo3(resultList.get(2))
                .drwtNo4(resultList.get(3))
                .drwtNo5(resultList.get(4))
                .drwtNo6(resultList.get(5))
                .bonusBall(bonusBall)
                .user(findUser)
                //.sixBall(resultList.get(0)+","+resultList.get(1)+","+resultList.get(2)+","+resultList.get(3)+","+resultList.get(4)+","+resultList.get(5))
                .storageCycle(cycle)
                .build();
        return buildCycleStorage;
    }
    //---
}
