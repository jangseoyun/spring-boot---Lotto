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

    public void insertLotto(Long no) {
        for (int i = 1; i <= no; i++) {
            String json = restApiService.readUrl(lottoHost + "?method=getLottoNumber&drwNo=" + i);
            Gson gson = new Gson();
            LottoApi lotto = gson.fromJson(json, LottoApi.class);

            //디비 등록
            lottoNumSave(lotto);
            log.info("lotto controller: " + lotto.toString());
        }
    }

}
