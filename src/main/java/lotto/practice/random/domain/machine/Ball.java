package lotto.practice.random.domain.machine;

import lombok.Getter;

import java.util.Random;

@Getter
public class Ball {
    private static final int MAX = 45;
    private final Integer value;
    //값 객체

    public Ball() {
        this.value = new Random().nextInt(MAX);//Ball
        //1~45
    }
}
