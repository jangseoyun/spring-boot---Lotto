package lotto.practice.random.domain.winningAmount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;
import lotto.practice.random.domain.winningAmount.rank.TotalCountFactory;
import lotto.practice.random.infrastructure.WinnerDbRepository;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WinningAmountService {

    private final WinnerDbRepository winnerDbRepository;
    private final WinningDbRepository winningDbRepository;

    /**
     * 회차 전체 리스트 가져오기
     */
    public List<Long> getCycleNumList() {
        List<Long> cycleNumList = winnerDbRepository.getCycleNumList();
        log.info("회차 전체 리스트 : " + cycleNumList);
        return cycleNumList;
    }

    /**
     * 등위별 총 카운트 가져오기
     */
    private TotalCount getRankTotalCount() {
        int fifth = winningDbRepository.getRankTotalCount(RankType.FIFTH);
        int fourth = winningDbRepository.getRankTotalCount(RankType.FOURTH);
        int third = winningDbRepository.getRankTotalCount(RankType.THIRD);
        int second = winningDbRepository.getRankTotalCount(RankType.SECOND);
        int first = winningDbRepository.getRankTotalCount(RankType.FIRST);

        //다 같은 타입이기때문에 지정해주기 위해서 factory로 직접 주입
        TotalCount rankTotalCount = TotalCountFactory.setRankTotalCount(fifth, fourth, third, second, first);
        return rankTotalCount;
    }


}
