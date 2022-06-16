package lotto.practice.random.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static lotto.practice.random.controller.LottoController.PRICE;

@Slf4j
public class Machine {

    private Ball ball;
    Random randomNum = new Random();
    //lottoNumVo 담는 List ==> HashSet<Integer> lottoNumVo
    List<HashSet<Integer>> lottoList = new ArrayList<>();

    /**
     * 1. 구입 갯수 반환
     */
    public int buyNum(int buying){
        log.debug("buying: "+buying);
        int num = (buying/PRICE);
        return num;
    }

    /**
     * 2. 번호 추출 (ball이 6개가 될때까지) + 파라미터 포함
     * 전체자동 : 구입한 갯수 만큼
     * 전체수동 : 구입한 갯수 만큼 (사용자에게 번호를 받음) -> 나중에 금액이랑 선택한 볼 수가 일치하는지도 확인해줘야함
     * 반자동 : 구입한 갯수 만큼 (사용자가 입력하지 않은 번호 랜덤으로 반환)
     */

    public List<HashSet<Integer>> allAutoNumSix(int buyNumResult){
        //전체 자동
        for(int y = 0; y<buyNumResult; y++){
            HashSet<Integer> lottoNumVo = new HashSet<Integer>();

            for(int x = 0; lottoNumVo.size()<6; y++){
                int ball = randomNum.nextInt(45);
                lottoNumVo.add(ball);
            }

            lottoList.add(y, lottoNumVo);
            log.debug((y+1)+"번째=> "+lottoNumVo+" ");

        }
        return lottoList;
    }

    public int selectNumSix(String numInput, int buyNum){
        //반자동

        return 0;
    }

    public int allSelectNumSix(String numInput, int buyNum){
        //전체 수동 -> 리스트로 받아야 할 것 같은데...

        return 0;
    }





}
