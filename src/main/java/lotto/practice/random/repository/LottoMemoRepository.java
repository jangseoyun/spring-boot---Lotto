package lotto.practice.random.repository;

import lotto.practice.random.domain.Ball;
import lotto.practice.random.vo.BallVo;
import org.springframework.stereotype.Repository;

@Repository
public class LottoMemoRepository implements LottoRepository{

    @Override
    public Ball numSix(BallVo ballVo) {
        return null;
    }


}
