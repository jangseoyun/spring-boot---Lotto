package lotto.practice.random.domain.winning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.domain.lottoapi.LottoApi;
import lotto.practice.random.domain.machine.MachineCycleStorage;
import lotto.practice.random.domain.winning.domain.FindWinning;
import lotto.practice.random.domain.winning.dto.WinningDto;
import lotto.practice.random.domain.winning.entity.WinningInfo;
import lotto.practice.random.infrastructure.WinningDbRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)//읽기 전용이랑 등록이랑 분리하여 작성하는것이 좋은가? 아니면 그냥 어노테이션으로 분리시켜둬도 상관없는가?
public class WinningService {

    private final WinningDbRepository winningDbRepository;
    private final FindWinning findWinning;

    //이번주 회차 번호 가지고 오기
    public String getLastCycleNum() {

    }

    //회차별 당첨 번호 로직으로도 사용할 수 있음
    public List<MachineCycleStorage> getWinner(String drwNo) {
        LottoApi getThisWeekWinning = winningDbRepository.getThisWeekWinning(drwNo);
        log.info("회차 당첨 번호 = " + getThisWeekWinning);

        List<MachineCycleStorage> findAllUser = winningDbRepository.findAllUser();
        log.info("이번회차 사용자 리스트 = " + findAllUser);

        List<WinningDto> winnerList = findWinning.getWinnerList(getThisWeekWinning, findAllUser);
        log.info("이번회차 당첨자 정보 리스트 = " + winnerList);

        List<Integer> totalCount = findWinning.makeTotalCount();
        log.info("totalCount = " + totalCount);

        Map<String, Long> winningAmount = findWinning.calculateAmount(getThisWeekWinning.getTotSellamnt(), totalCount);
        log.info("등위별 총당첨 금액 = " + winningAmount);

        //winner 등록
        for (WinningDto winner : winnerList) {
            WinningInfo createWinner = WinningFactory.createWinningInfo(winner, winningAmount, totalCount);
            winningDbRepository.saveWinner(createWinner);
        }

        return findAllUser;
    }

    //이걸 메서드를 나눠서 작동할 수 있도록
    //예를 들면 findwinning안에서 이루어지는 로직은 그안에서 다 처리하고 받을 수 있도록하고
    //getWinner이면 getWinner만, winner등록하는 로직은 등록만하는 메서드로 만들것


}
