package lotto.practice.random.domain.winningAmount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;
import lotto.practice.random.domain.winningAmount.rank.TotalCountFactory;
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
        TotalCount rankTotalCount = getRankTotalCount(lottoCycleNum);//당회차 등위별 당첨자 수
        Long totalSellAmount = winningDbRepository.getTotalSellAmount(lottoCycleNum);//당회차 전체 판매 금액

        //등위별 금액 계산
        RankAmountCommand getRankAmount = calculatAmount.getWinningAmount(rankTotalCount, totalSellAmount);
        return getRankAmount;
        //1인당 당첨금은 command에서 나누기!
    }

    /**
     * 등위별 총 카운트 가져오기
     */
    private TotalCount getRankTotalCount(Long lottoCycleNum) {
        int fifth = winningDbRepository.getRankTotalCount(RankType.FIFTH, lottoCycleNum);
        int fourth = winningDbRepository.getRankTotalCount(RankType.FOURTH, lottoCycleNum);
        int third = winningDbRepository.getRankTotalCount(RankType.THIRD, lottoCycleNum);
        int second = winningDbRepository.getRankTotalCount(RankType.SECOND, lottoCycleNum);
        int first = winningDbRepository.getRankTotalCount(RankType.FIRST, lottoCycleNum);

        //다 같은 타입이기때문에 지정해주기 위해서 factory로 직접 주입
        TotalCount rankTotalCount = TotalCountFactory.setRankTotalCount(fifth, fourth, third, second, first);
        return rankTotalCount;
    }






}
