package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.RankType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WinningDbRepository {

    private final EntityManager em;

    public int getRankTotalCount(RankType rank) {
        return em.createQuery(
                        "select count(winner.winnerRank) from Winner winner" +
                                " where winner.winnerRank = :rank", Integer.class)
                .setParameter("rank", rank
                ).getSingleResult();
    }


}
