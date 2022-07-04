package lotto.practice.random.service;

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

    public String readUrl(String urlPath) throws Exception{
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read = -1;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    public Lotto lottoNumSave(Lotto lotto){
        Lotto createLotto = Lotto.createLotto(lotto);
        return lottoApiRepository.lottoNumSave(createLotto);
    }


}
