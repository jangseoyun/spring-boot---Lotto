package lotto.practice.random.domain.Winner.repository;

import lotto.practice.random.domain.Winner.entity.Winner;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;

import java.util.List;

public interface WinnerRepository {

    //회차 리스트
    List<Long> getCycleNumList();

    //이번주 회차 번호 가지고 오기
    Long getCycleNum();

    //이번주 회차 데이터 가져오기
    LottoApi getThisWeekWinning(Long drwNo);

    //cycleStorage에서 sixNum과 일치하는 데이터 가져옴
    List<MachineCycleStorage> getWinner(String sixNum);

    //당첨자 저장
    Long saveWinner(Winner winner);

    //이번 회차 추첨 사용자 전체 가져오기
    List<MachineCycleStorage> findAllUser();

    //당첨자 전체 가지고오기
    List<Winner> findAllWinner();
}
