package lotto.practice.random.domain.winning;

import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.dto.WinningDto;

import java.util.Map;

@Slf4j
public class WinningFactory {

    public static WinningDto createWinningInfo(WinningDto findWinnerRank, Map<String, Object> winnerAmount, LottoApi getThisWeekWinning, MachineCycleStorage userOne) {

        //TODO: 나머지 채워넣기
        return WinningDto.builder()
                .sixBall(userOne.getSixBall())
                .totSellingPrice(getThisWeekWinning.getTotSellamnt())
                .user(userOne.getUser())
                .lottoCycleNum(getThisWeekWinning.getDrwNo())
                .winnerRank(findWinnerRank.getWinnerRank())
                .totalAmount(getTotalAmount(winnerAmount, findWinnerRank.getWinnerRank()))
                .winnerAmount()
                .winnerTotalCount()
                .bonusBall(userOne.getBonusBall())
                .build();
    }

    /*public static Long getTotalAmount(Map<String, Object> winnerAmount, int userRank){
        log.info("winnerAmount = " + winnerAmount);
        if(userRank == 1){
            return winnerAmount.get("firstAmount");
        } else if (userRank == 2 || userRank == 3) {
            return winnerAmount.get("secondAndThirdAmount");
        } else if (userRank == 4) {
            return winnerAmount.get("fourthTotalAmount");
        } else if (userRank == 5) {
            return winnerAmount.get("fifthTotalAmount");
        }else {
            return 0;
        }
    }*/


}
