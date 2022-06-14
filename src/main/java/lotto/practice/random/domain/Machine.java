package lotto.practice.random.domain;

import lotto.practice.random.repository.InputVo;

import static lotto.practice.random.controller.LottoController.PRICE;

public class Machine {

    /**
     * 번호 추출 비즈니스 로직
     */

    //1. 구입 갯수 반환
    public int buyNum(int buying){
        int num = (buying/PRICE);
        return num;
    }

    //2. 번호 추출 (ball이 6개가 될때까지) + 파라미터 포함
    public int numSix(){

        //랜덤으로 뽑아서 ball 주기

        return 0;
    }






}
