package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.entity.WinningInfo;
import lotto.practice.random.domain.winning.repository.WinningRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WinningDbRepository implements WinningRepository {

    private final EntityManager em;

    //이번주 회차 번호 가지고 오기ß
    @Override
    public String getCycleNum() {
        return em.createQuery(
                        "select max(lottoApi.drwNo) as max_cyclenum from LottoApi lottoApi")
                .getSingleResult();

    }

    //이번주 회차 데이터 가져오기 "엔티티로 받고! straem() dto로 변환해야함"
    @Override
    public LottoApi getThisWeekWinning(String drwNo) {
        return em.createQuery(
                        "select api from LottoApi api" +
                                " where api.drwNo = :drwNo", LottoApi.class)
                .setParameter("drwNo", drwNo)
                .getSingleResult();//null값이 들어오는 경우 오류가 생길 수 있으나, 본 경우는 null값이 들어오면 안되기 때문에 사용
    }

    //cycleStorage에서 sixNum과 일치하는 데이터 가져옴
    //sixNum toString 형태 일치 시켜야함
    @Override
    public List<MachineCycleStorage> getWinner(String sixNum) {
        log.info("api sixNum = " + sixNum);
        //String test = "5,41,12,13,31,32";
        return em.createQuery(
                        "select cs from MachineCycleStorage cs" +
                                " where cs.sixBall = :sixNum", MachineCycleStorage.class)
                .setParameter("sixNum", sixNum)
                .getResultList();
    }

    @Override
    public Long saveWinner(WinningInfo winningInfo) {
        em.persist(winningInfo);
        return winningInfo.getNo();
    }

    @Override
    public List<MachineCycleStorage> findAllUser() {
        return em.createQuery(
                        "select cs From MachineCycleStorage cs")
                .getResultList();
    }


}
