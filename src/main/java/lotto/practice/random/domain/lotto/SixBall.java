package lotto.practice.random.domain.lotto;

import lotto.practice.random.domain.machine.Ball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SixBall {
    private List<Ball> list;
    Ball ball = new Ball();

    public SixBall() {
        this.list = new ArrayList<>(makeBallSet());
    }

    //TODO:
    public Ball getNo1() {
        return list.get(0);
    }

    public Ball getNo2() {
        return list.get(1);
    }

    public Ball getNo3() {
        return list.get(2);
    }

    public Ball getNo4() {
        return list.get(3);
    }

    public Ball getNo5() {
        return list.get(4);
    }

    public Ball getNo6() {
        return list.get(5);
    }

    @Override
    public String toString() {
        return getNo1() + "," + getNo2() + "," + getNo3() + "," + getNo4() + "," + getNo5() + "," + getNo6();
    }

    private HashSet<Ball> makeBallSet() {
        HashSet<Ball> hashSet = new HashSet<>();

        for (int i = 0; hashSet.size() < 6; i++) {
            hashSet.add(ball);
        }
        return hashSet;
    }
}
