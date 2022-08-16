package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.entity.Winner;
import lotto.practice.random.domain.Winner.repository.WinnerRepository;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WinnerDbRepository implements WinnerRepository {

    private final EntityManager em;

    //회차 전체 리스트
    @Override
    public List<Long> getCycleNumList() {
        return em.createQuery(
                        "select api.drwNo from LottoApi api", Long.class)
                .getResultList();
    }

    //이번주 회차 번호 가지고 오기
    @Override
    public Long getCycleNum() {
        return em.createQuery(
                        "select max(api.drwNo) from LottoApi api"
                        , Long.class)
                .getSingleResult();
    }

    //이번주 회차 데이터 가져오기 "엔티티로 받고! straem() dto로 변환해야함"
    @Override
    public LottoApi getThisWeekWinning(Long drwNo) {
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
    public Long saveWinner(Winner winner) {
        em.persist(winner);
        return winner.getNo();
    }

    @Override
    public List<MachineCycleStorage> findAllUser() {
        return em.createQuery(
                        "select cs From MachineCycleStorage cs")
                .getResultList();
    }

    @Override
    public List<Winner> findAllWinner() {
        return em.createQuery("select winner from Winner winner", Winner.class)
                .getResultList();
    }


}
