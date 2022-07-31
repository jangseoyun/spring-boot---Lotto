package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.WinningRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class WinningDbRepository implements WinningRepository {

    private final EntityManager em;

    //이번주 회차 데이터 가져오기 "엔티티로 받지말고 dto로 변환해야함"
    @Override
    public LottoApi getThisWeekWinning(Long drwNo) {
        return em.createQuery(
                        "select la from LottoApi la" +
                                " where la.drwNo = :drwNo", LottoApi.class)
                .setParameter("drwNo", drwNo)
                .getSingleResult();
    }

    //cycleStorage에서 sixNum과 일치하는 데이터 가져옴
    @Override
    public MachineCycleStorage getWinner(String sixNum) {
        return null;
    }
}
