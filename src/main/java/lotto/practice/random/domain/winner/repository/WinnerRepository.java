package lotto.practice.random.domain.winner.repository;

import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winner.entity.Winner;
import lotto.practice.random.domain.winningInfo.WinningInfo;

import java.util.List;

public interface WinnerRepository {

    //회차 리스트
    List<Long> getCycleNumList();

    //이번주 회차 데이터 가져오기
    LottoApi getThisWeekWinning(Long drwNo);

    //cycleStorage에서 sixNum과 일치하는 데이터 가져옴
    List<MachineCycleStorage> getWinner(String sixNum);

    //당첨자 저장
    Long saveWinner(Winner winner);

    //이번 회차 추첨 사용자 전체 가져오기
    List<MachineCycleStorage> findAllUser();

    List<MachineCycleStorage> getUserCycleStorage(Long cycleNum, Long userNo);

    //당첨자 전체 가지고오기
    List<Winner> findAllWinner();

    //사용자 당회차 당첨 정보 가져오기
    List<WinningInfo> userGameResult(String userId, Long lottoCycleNum);

}
