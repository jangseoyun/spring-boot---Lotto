package lotto.practice.random.domain.machine;

import lombok.Getter;

import java.util.Random;

@Getter
public class Ball {
    private static final int MAX = 45;
    private final Integer value;
    //값 객체

    public Ball() {
        this(new Random().nextInt(MAX));//메인 생성자 호출
        //1~45
    }

    public Ball(Integer value) {//메인 생성자
        if (value < 1 || value > MAX) {
            throw new IllegalArgumentException("1~45 사이의 숫자만 가능합니다");
        }
        this.value = value;
    }
}
