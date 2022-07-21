package lotto.practice.random.infrastructure;


import lombok.RequiredArgsConstructor;
import lotto.practice.random.domain.lottoapi.LottoApi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LottoApiRepository {

    private final EntityManager em;

    public LottoApi lottoNumSave(LottoApi lotto){
        em.persist(lotto);
        return lotto;
    }

}
