package lotto.practice.random.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.entity.Lotto;
import lotto.practice.random.repository.LottoApiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LottoApiService {

    private final LottoApiRepository lottoApiRepository;
    private final RestAPIService restAPIService;

    public Lotto lottoNumSave(Lotto lotto){
        Lotto createLotto = Lotto.createLotto(lotto);
        return lottoApiRepository.lottoNumSave(createLotto);
    }

    public void insertLotto(Long no) {
        for(int i = 1; i <= no; i++){
            String json = restAPIService.readUrl("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + i);
            Gson gson = new Gson();
            Lotto lotto = gson.fromJson(json, Lotto.class);

            //디비 등록
            lottoNumSave(lotto);
            log.info("lotto controller: " + lotto.toString());
        }
    }
}
