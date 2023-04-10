package lotto.practice.random.domain.machine.converter;

import lotto.practice.random.domain.machine.Ball;

import javax.persistence.AttributeConverter;

public class BallConverter implements AttributeConverter<Ball, Integer> {

    @Override
    //ball을 받아서 integer로 타입을 변환
    public Integer convertToDatabaseColumn(Ball attribute) {
        return attribute.getValue();
    }

    @Override
    public Ball convertToEntityAttribute(Integer dbData) {
        return new Ball(dbData);
    }

}
