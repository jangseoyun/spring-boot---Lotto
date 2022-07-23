package lotto.practice.random.domain.machine;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lotto.SixBall;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    //repository 인터페이스
    Random randomNum = new Random();

    /**
     * 1. 구입한 갯수
     */
    /*private int buyNumber(int buying){
        int buyNum = (buying/PRICE);
        log.debug("구입 갯수 : " + buyNum);
        return buyNum;
    }*/

    /**
     * 2-1. 번호 추출 (ball이 6개가 될때까지)
     * * 전체자동 : 구입한 갯수 만큼
     */
    public List<SixBall> allAutoSixBall(int count) {
        //int buyNum = buyNumber(price);

        //2. 전체 자동
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
    public void selectNumSixBall(int buying, String[] inputNum) {


    }

    public void allSelectSixBall(Set<Integer> numInput) {
        //전체 수동 -> 리스트로 받아야 할 것 같은데...

    }


    /**
     * 보너스 번호
     */
    public int bonusBall() {
        int bonusNum = randomNum.nextInt(45);
        log.debug("보너스 번호 추출: " + bonusNum);
        return bonusNum;
    }


}