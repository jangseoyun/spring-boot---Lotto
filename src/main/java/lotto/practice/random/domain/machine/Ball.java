package lotto.practice.random.domain.machine;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Random;

@Getter
@EqualsAndHashCode(of = "value")//필드가 여러개 일 때 검증하는 기준을 설정해줘야한다 equals를 재정의한것
public class Ball {
    private static final int MAX = 45;
    private final Integer value;
    //값 객체

    public Ball() {
        this(new Random().nextInt(MAX) + 1);//메인 생성자 호출
        //1~45
    }

    public Ball(Integer value) {//메인 생성자
        if (value < 1 || value > MAX) {
            throw new IllegalArgumentException("1~45 사이의 숫자만 가능합니다");
        }
        this.value = value;
    }

}
