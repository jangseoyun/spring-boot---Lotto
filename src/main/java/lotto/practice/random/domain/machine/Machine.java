package lotto.practice.random.domain.machine;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lotto.SixBall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 랜덤 번호 추출 비즈니스 로직
 * * 전체자동 : 구입한 갯수 만큼
 * * 반자동 : 구입한 갯수 만큼 (사용자가 입력하지 않은 번호 랜덤으로 반환)
 * * 전체수동 : 구입한 갯수 만큼 (사용자에게 번호를 받음) -> 나중에 금액이랑 선택한 볼 수가 일치하는지도 확인해줘야함
 */
@Slf4j
@Component
public class Machine {
    List<SixBall> machineResult = new ArrayList<>();

    /**
     * 2-1. 번호 추출 (구입한 티켓수 만큼 번호 뽑아줌)
     * * 전체자동 : 구입한 갯수 만큼
     */
    public List<SixBall> allAutoSixBall(int count) {
        for (int y = 0; y < count; y++) {
            machineResult.add(new SixBall());
        }
        log.info("machineResult : " + machineResult);
        return machineResult;
    }

    /**
     * 2-2. 번호 추출 (ball이 6개가 될때까지)
     * * 반자동 : 사용자 입력 번호 이외 자동 추출
     */
    public List<SixBall> selectNumSixBall(int count, List<Integer> inputNumList) {
        //TODO: 티켓마다 inputNum이 중복으로 들어가는 문제 해결하기
        for (int i = 0; i < count; i++) {
            machineResult.add(new SixBall(inputNumList));
        }
        log.info("selectNumSixBall = " + machineResult);
        return machineResult;
    }

    public void allSelectSixBall(Set<Integer> inputNum) {
        //전체 수동 -> 리스트로 받아야 할 것 같은데...

    }

}
