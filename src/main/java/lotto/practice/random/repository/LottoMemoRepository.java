package lotto.practice.random.repository;

import lotto.practice.random.domain.Ball;
import org.springframework.stereotype.Repository;

@Repository
public class LottoMemoRepository implements LottoRepository{

    @Override
    public Ball save(Ball ball) {
        return null;
    }


}
