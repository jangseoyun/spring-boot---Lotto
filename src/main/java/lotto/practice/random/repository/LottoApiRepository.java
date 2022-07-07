package lotto.practice.random.repository;


import lombok.RequiredArgsConstructor;
import lotto.practice.random.entity.Lotto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LottoApiRepository {

    private final EntityManager em;

    public Lotto lottoNumSave(Lotto lotto){
        em.persist(lotto);
        return lotto;
    }

}
