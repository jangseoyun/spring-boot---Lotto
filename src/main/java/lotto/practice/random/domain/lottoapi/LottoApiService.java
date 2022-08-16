package lotto.practice.random.domain.lottoapi;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final LottoApiRepository lottoApiRepository;
    private final RestApiService restApiService;

    public LottoApi lottoNumSave(LottoApi lotto) {
        LottoApi createLotto = LottoApi.createLotto(lotto);
        return lottoApiRepository.lottoNumSave(createLotto);
    }

    /**
     * 로또 API 전체(1~요청회차) 저장
     */
    public void insertLotto(Long no) {
        for (int i = 1; i <= no; i++) {
            insertOne(no);
        }
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

}
