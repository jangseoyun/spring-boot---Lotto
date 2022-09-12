package lotto.practice.random.infrastructure;


import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.lottoapi.LottoApi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LottoApiRepository {
    private final EntityManager em;

    //동행복권 lotto Api 저장
    public LottoApi lottoNumSave(LottoApi lotto) {
        em.persist(lotto);
        return lotto;
    }

    //이번주 회차 번호 가지고 오기
    public Long getCycleNum() {
        return em.createQuery(
                        "select max(api.drwNo) from LottoApi api"
                        , Long.class)
                .getSingleResult();
    }

    //단일 회차 당첨번호 요청
    public LottoApi getLottoResult(Long lottoCycleNum) {
        return em.createQuery("select api from LottoApi api where api.drwNo = :lottoCycleNum"
                        , LottoApi.class)
                .setParameter("lottoCycleNum", lottoCycleNum)
                .getSingleResult();
    }

}
