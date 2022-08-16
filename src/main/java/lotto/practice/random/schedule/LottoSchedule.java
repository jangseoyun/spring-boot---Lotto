package lotto.practice.random.schedule;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.Winner.WinnerService;
import lotto.practice.random.domain.lottoapi.LottoApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static lotto.practice.random.common.MemoryContext.MemoryKey.LAST_CYCLE_NUM;
import static lotto.practice.random.common.MemoryContext.memory;

@Component
@RequiredArgsConstructor
public class LottoSchedule {//controller로 이해

    private final WinnerService winnerService;
    private final LottoApiService lottoApiService;

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(cron = "0 0 0 * * SUN")
    public void lottoLastCycleFetch() {
        Long lastCycleNum = winnerService.getLastCycleNum();
        memory.put(LAST_CYCLE_NUM, (lastCycleNum += 1));
        lottoApiService.insertOne(lastCycleNum);//가져온 회차 api DB 저장 로직
        System.out.println("lastCycleNum = " + lastCycleNum);
    }

    //값이 하나인 컬렉션

    //자바 8 --> optional , Stream, LAMBDA
    /*public void test(@Nullable User user) {
        Optional<User> userOptional = Optional.ofNullable(user);
        if (userOptional.isPresent()) {
            User user4 = userOptional.get();
        }

        //값이 이 없으면 throw
        User user2 = userOptional.orElseThrow();
        User user3 =userOptional.orElseThrow(() -> new RuntimeException("값이 없어요"));

        //
        userOptional.ifPresent(System.out::println);
        userOptional.ifPresent(System.out::println);

        userOptional.filter(x -> x.getUserId().equals("aaaaa")).isPresent();
        userOptional.flatMap(...);
        userOptional.map(...)


    }
*/

}
