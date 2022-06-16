package lotto.practice.random.repository;

import lotto.practice.random.domain.Ball;
import lotto.practice.random.vo.BallVo;

public interface LottoRepository {

    //데이터 저장소
    Ball numSix(BallVo ballVo);

}
