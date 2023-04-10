package lotto.practice.random.domain.lotto;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.Ball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@EqualsAndHashCode(of = "list")
public class SixBall {

    private static final int LENGTH = 6;
    private final List<Ball> list;

    public SixBall() {
        this.list = new ArrayList<>(makeBallSet());
    }

    public SixBall(List<Integer> inputNumList) {
        this.list = new ArrayList<>(requestUserNum(inputNumList));
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

    private HashSet<Ball> makeBallSet() {
        HashSet<Ball> hashSet = new HashSet<>(); //여기서 받으면 hashSet에 받은 받을 넣어줘야함
        //만약에 받은 번호가 있으면 그 번호 포함 length만큼 생성
        makeBallLength(hashSet);
        return hashSet;
    }

    private HashSet<Ball> requestUserNum(List<Integer> inputNumList) {
        HashSet<Ball> hashSet = new HashSet<>();

        //만약에 받은 번호가 있으면 그 번호 포함 length만큼 생성
        //TODO: 아... 여기서  hashSet으로 받으니까 순서 보장안됨 List로 하면서 중복되는 숫자 제거할 수 있도록
        for (Integer integer : inputNumList) {//1개씩
            hashSet.add(new Ball(integer));
        }
        makeBallLength(hashSet);

        log.info("hashSet = " + hashSet);
        return hashSet;
    }

    private void makeBallLength(HashSet<Ball> hashSet) {
        while (hashSet.size() < LENGTH) {
            hashSet.add(new Ball());
        }
    }

    @Override
    public String toString() {
        return getNo1().getValue() + ","
                + getNo2().getValue() + ","
                + getNo3().getValue() + ","
                + getNo4().getValue() + ","
                + getNo5().getValue() + ","
                + getNo6().getValue();
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
