package lotto.practice.random.domain.machine;

import lotto.practice.random.domain.user.User;
import lotto.practice.random.dto.InputDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class MachineFactory {

    //1. 출력된 공 꺼내기
    //2. 유저 가져오기

    public static MachineCycleStorage createStorage(InputDto inputDto, List<HashSet<Integer>> allAutoResult, int bonusBall, User findUser){
        //리스트로 변경 set -> list
        for (HashSet<Integer> resultVo : allAutoResult) {
            List<Integer> resultList = new ArrayList<>(resultVo);

            for (int i = 0; i < resultList.size(); i++){
                MachineCycleStorage buildCycleStorage = MachineCycleStorage.builder()
                        .drwtNo1(resultList.get(0))
                        .drwtNo2(resultList.get(1))
                        .drwtNo3(resultList.get(2))
                        .drwtNo4(resultList.get(3))
                        .drwtNo5(resultList.get(4))
                        .drwtNo6(resultList.get(5))
                        .bonusBall(bonusBall)
                        .user(findUser)
                        .sixBall(resultList.get(0)+","+resultList.get(1)+","+resultList.get(2)+","+resultList.get(3)+","+resultList.get(4)+","+resultList.get(5))
                        .storageCycle("1555회")
                        .build();
                return buildCycleStorage;
            }

        }

    }
    //---
}
