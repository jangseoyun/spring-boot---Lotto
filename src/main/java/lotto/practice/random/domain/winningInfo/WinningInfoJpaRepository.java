package lotto.practice.random.domain.winningInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinningInfoJpaRepository extends JpaRepository<WinningInfo, Long> {
    List<WinningInfo> findBylottoCycleNum(Long lottoCycleNum);
}
