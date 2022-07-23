package lotto.practice.random.domain.lotto;

import lotto.practice.random.domain.machine.Ball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class SixBall {
    private List<Ball> context;

    public SixBall() {
        Random randomNum = new Random();
        HashSet<Integer> hashSet = new HashSet<>();

        //TODO: i로 변경
        for (int x = 0; hashSet.size() < 6; x++) {
            int ballVo = randomNum.nextInt(45);//Ball
            hashSet.add(ballVo);
        }

        this.context = new ArrayList<>(hashSet);
    }

    //TODO:
    public Ball getNo1() {
        return context.get(0);
    }

    public Integer getNo2() {
        return context.get(1);
    }

    public Integer getNo3() {
        return 0;
    }

    public Integer getNo4() {
        return 0;
    }

    public Integer getNo5() {
        return 0;
    }

    public Integer getNo6() {
        return 0;
    }

    @Override
    public String toString() {
        return getNo1() + "," + drwtNo2 + "," + drwtNo3 + "," + drwtNo4 + "," + drwtNo5 + "," + drwtNo6
    }
}
