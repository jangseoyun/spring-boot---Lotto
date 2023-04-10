package lotto.practice.random.infrastructure.repository;

import lotto.practice.random.domain.winningInfo.WinningInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinningInfoJpaRepository extends JpaRepository<WinningInfo, Long> {
    List<WinningInfo> findByLottoCycleNum(Long lottoCycleNum);
}
