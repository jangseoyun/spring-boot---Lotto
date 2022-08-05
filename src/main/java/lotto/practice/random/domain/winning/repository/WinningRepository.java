package lotto.practice.random.domain.winning.repository;

import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.entity.WinningInfo;

import java.util.List;

public interface WinningRepository {

    //이번주 회차 번호 가지고 오기
    String getCycleNum();

    //이번주 회차 데이터 가져오기
    LottoApi getThisWeekWinning(String drwNo);

    //cycleStorage에서 sixNum과 일치하는 데이터 가져옴
    List<MachineCycleStorage> getWinner(String sixNum);

    //당첨자 저장
    Long saveWinner(WinningInfo winningInfo);

    //이번 회차 추첨 사용자 전체 가져오기
    List<MachineCycleStorage> findAllUser();
}
