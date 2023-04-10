package lotto.practice.random.domain.lottoapi;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.dto.LottoApiDto;
import lotto.practice.random.infrastructure.LottoApiRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LottoApiService {

    @Value("${api.lotto.host}")
    private String lottoHost;
    private final RestApiService restApiService;
    private final LottoApiRepository lottoApiRepository;

    /**
     * 이번주 회차 번호 가지고 오기
     */
    //@Async//비동기 호출 -> 사용하려면 컨피크에 등록해줘야한다
    public Long getLastCycleNum() {
        Long getLastCycelNum = lottoApiRepository.getCycleNum();
        log.info("이번주 회차 번호 : " + getLastCycelNum);
        return getLastCycelNum;
    }

    /**
     * 로또 API 전체(1~요청회차) 저장
     */
    public void insertLotto(Long no) {
        for (Long i = 1L; i <= no; i++) {
            insertOne(i);
        }
    }

    /**
     * 단일 회차 당첨번호 요청
     */
    public LottoApiDto getLottoResult(Long lottoCycleNum) {
        LottoApi getLottoResult = lottoApiRepository.getLottoResult(lottoCycleNum);
        log.info("getLottoResult : " + getLottoResult);
        return LottoApiFactory.toLottoApiDto(getLottoResult);
    }


    /**
     * 매주 일요일 자동 요청 후 저장(단일 회차)
     */
    public void insertOne(Long no) {
        String json = restApiService.readUrl(lottoHost + "?method=getLottoNumber&drwNo=" + no);
        Gson gson = new Gson();
        LottoApi lotto = gson.fromJson(json, LottoApi.class);

        //디비 등록
        lottoNumSave(lotto);
        log.info("lotto controller: " + lotto.toString());

        try {//로직의 시간을 지연시키는 것
            Thread.sleep(100);//0.1초
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private LottoApi lottoNumSave(LottoApi lotto) {
        LottoApi createLotto = LottoApi.createLotto(lotto);
        return lottoApiRepository.lottoNumSave(createLotto);
    }


}
