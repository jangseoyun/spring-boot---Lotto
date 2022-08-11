package lotto.practice.random.domain.winningAmount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WinningAmountService {

    private final WinningDbRepository winningDbRepository;
    private final CalculatAmount calculatAmount;

    /**
     * 당회차 등위별 당첨금 계산
     */
    public RankAmountCommand calcAmount(Long lottoCycleNum) {
        TotalCount rankTotalCount = calculatAmount.getRankTotalCount(lottoCycleNum);//당회차 등위별 당첨자 수
        Long totalSellAmount = winningDbRepository.getTotalSellAmount(lottoCycleNum);//당회차 전체 판매 금액

        //등위별 금액 계산
        RankAmountCommand getRankAmount = calculatAmount.getWinningAmount(rankTotalCount, totalSellAmount);
        return getRankAmount;
    }








}
