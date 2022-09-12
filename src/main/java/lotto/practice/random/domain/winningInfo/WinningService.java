package lotto.practice.random.domain.winningInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.user.User;
import lotto.practice.random.domain.winner.entity.Winner;
import lotto.practice.random.infrastructure.WinnerDbRepository;
import lotto.practice.random.infrastructure.repository.UserJpaRepository;
import lotto.practice.random.infrastructure.repository.WinningInfoJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WinningService {

    private final WinnerDbRepository winnerDbRepository;
    private final WinningInfoJpaRepository winningInfoJpaRepository;
    private final UserJpaRepository userJpaRepository;

    /**
     * 회차 전체 리스트 가져오기
     */
    public List<Long> getCycleNumList() {
        List<Long> cycleNumList = winnerDbRepository.getCycleNumList();
        log.info("회차 전체 리스트 : " + cycleNumList);
        return cycleNumList;
    }

    /**
     * 화면에 보낼 winningInfo 만들기
     */
    @Transactional
    public void makeWinningInfo(WinningResult winningResult) {
        //winnerList 가지고 오기 winner + 등수에 따라 RankAmountCommand 필요
        List<Winner> allWinner = winnerDbRepository.findAllWinner();
        for (Winner winner : allWinner) {
            WinningInfo createWinningInfo = WinningInfoFactory.setWinningInfo(winner, winningResult);
            winningInfoJpaRepository.save(createWinningInfo);//DB 저장 -> winningInfo
        }
    }

    /**
     * 회차별 winningInfo 가져오기
     */
    public List<WinningInfo> findWinningInfo(Long lottoCycleNum) {
        return winningInfoJpaRepository.findByLottoCycleNum(lottoCycleNum);
    }

    /**
     * 사용자 회차별 당첨 여부 확인
     */
    public List<WinningInfo> userGameResult(String userId, Long lottoCycleNum) {
        return winnerDbRepository.userGameResult(userId, lottoCycleNum);
    }


    /**
     * 사용자 회차별 추첨리스트 가져오기
     */
    public List<MachineCycleStorage> getUserCycleStorage(Long cycleNum, Long userNo) {
        Optional<User> getUser = userJpaRepository.findById(userNo);
        if (getUser == null) {
            throw new NullPointerException("로그인을 해주세요.");
        }

        List<MachineCycleStorage> userCycleStorageList = winnerDbRepository.getUserCycleStorage(cycleNum, getUser.get().getNo());

        return userCycleStorageList;
    }

}
