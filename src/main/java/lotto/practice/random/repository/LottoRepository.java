package lotto.practice.random.repository;

import lotto.practice.random.domain.Ball;
import lotto.practice.random.entity.Lotto;
import lotto.practice.random.vo.BallVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto, Long> {




}
