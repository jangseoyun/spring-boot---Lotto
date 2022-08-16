package lotto.practice.random.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.Winner.RankType;
import lotto.practice.random.domain.winningInfo.RankAmountRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WinningDbRepository implements RankAmountRepository {

    private final EntityManager em;

    /**
     * 당회차 등위별 당첨자 수
     */
    public Long getRankTotalCount(RankType rank, Long lottoCycleNum) {
        return em.createQuery(
                        "select count(winnerRank) from Winner" +
                                " where winnerRank = :rank" +
                                " and lottoCycleNum = :lottoCycleNum", Long.class)
                .setParameter("rank", rank)
                .setParameter("lottoCycleNum", lottoCycleNum)
                .getSingleResult();
    }

    /**
     * 당회차 전체 판매 금액
     */
    public Long getTotalSellAmount(Long lottoCycleNum) {
        return em.createQuery(
                        "select api.totSellamnt from LottoApi api" +
                                " where api.drwNo = :lottoCycleNum", Long.class)
                .setParameter("lottoCycleNum", lottoCycleNum)
                .getSingleResult();
    }


}
