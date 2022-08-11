package lotto.practice.random.domain.winningAmount;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.winningAmount.rank.TotalCount;
import lotto.practice.random.domain.winningAmount.rank.TotalCountFactory;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CalculatAmount {

    private final WinningDbRepository winningDbRepository;

    /**
     * 등위별 총 카운트 가져오기
     */
    public TotalCount getRankTotalCount(Long lottoCycleNum) {
        int fifth = winningDbRepository.getRankTotalCount(RankType.FIFTH, lottoCycleNum);
        int fourth = winningDbRepository.getRankTotalCount(RankType.FOURTH, lottoCycleNum);
        int third = winningDbRepository.getRankTotalCount(RankType.THIRD, lottoCycleNum);
        int second = winningDbRepository.getRankTotalCount(RankType.SECOND, lottoCycleNum);
        int first = winningDbRepository.getRankTotalCount(RankType.FIRST, lottoCycleNum);

        //다 같은 타입이기때문에 지정해주기 위해서 factory로 직접 주입
        TotalCount rankTotalCount = TotalCountFactory.setRankTotalCount(fifth, fourth, third, second, first);
        return rankTotalCount;
    }

    /**
     * 등위별 금액 계산
     * 1등 :  (총 당첨금 - i ) * 0.75
     * 2등 :  (총 당첨금 - i ) * 0.125
     * 3등 :  (총 당첨금 -  i )* 0.125
     * 4등 :  (50,000 * 당첨자 수)
     * 5등 :  (5,000 * 당첨자 수)
     */
    public RankAmountCommand getWinningAmount(TotalCount rankTotalCount, Long totalSellAmount) {
        Long resultFifthAmount = fifthAmount(rankTotalCount.getFifthTotalCount()); //5등
        Long resultFourthAmount = fourthAmount(rankTotalCount.getFourthTotalCount()); //4등
        Long fifthNFourth = (resultFifthAmount + resultFourthAmount);

        Long thirdOrSecond = thirdNsecondAmount(fifthNFourth, totalSellAmount);//2등,3등
        Long resultFirstAmount = firstAmount(fifthNFourth, thirdOrSecond, totalSellAmount);//1등

        RankAmountCommand rankAmountCommand = new RankAmountCommand(resultFifthAmount, resultFourthAmount, thirdOrSecond, resultFirstAmount);
        return rankAmountCommand;
    }

    private Long fifthAmount(int fifthTotalCount) {
        return (fifthTotalCount * 5000L);
    }

    private Long fourthAmount(int fourthTotalCount) {
        return (fourthTotalCount * 50000L);
    }

    private Long thirdNsecondAmount(Long minusAmount, Long totalSellAmount) {
        return (totalSellAmount - minusAmount) * 125 / 1000; //그냥 25%로???
    }

    private Long firstAmount(Long minusAmount, Long thirdNsecondAmount, Long totalSellAmount) {
        long amount = minusAmount + (thirdNsecondAmount * 2);
        return (totalSellAmount - amount);
    }
}
