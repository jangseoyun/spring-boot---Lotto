package lotto.practice.random.domain.lotto;

import lombok.EqualsAndHashCode;
import lotto.practice.random.domain.machine.Ball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@EqualsAndHashCode(of = "list")
public class SixBall {

    private static final int LENGTH = 6;
    private final List<Ball> list;

    //TODO: selectNum 반자동
    public SixBall() {
        this.list = new ArrayList<>(makeBallSet());
    }

    public int size() {
        return list.size();
    }

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
    public String toString() {//** Stringbuffer
        return (getNo1() + "," + getNo2() + "," + getNo3() + "," + getNo4() + "," + getNo5() + "," + getNo6());
    }

    private HashSet<Ball> makeBallSet() {
        HashSet<Ball> hashSet = new HashSet<>();
        //만약에 받은 번호가 있으면 그 번호 포함 length만큼 생성
        while (hashSet.size() < LENGTH) {
            hashSet.add(new Ball());
        }
        return hashSet;
    }



    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SixBall sixBall = (SixBall) o;
        return Objects.equals(list, sixBall.list);
        리스트 6개의 값을 전부 체크해주는 코드 넣기
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }*/
}
