package lotto.practice.random.controller;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto.practice.random.entity.Lotto;
import lotto.practice.random.service.LottoApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Time: 03:41
 * Title: Lotto API
 * Desc: gson라이브러리를 통한 파싱과 데이터 루프
 *
 * == Modification Infomation ==
 * <p>
 * 수정일         수정자         수정내용
 * ------------ ------------ ----------------------------
 * 2022/07/05   Seoyun           최초생성
 *
 * @Version 11
 * @author  Seoyun Jang
 * @since   2022-07-05
 */

@RestController
@AllArgsConstructor
@Slf4j
public class LottoApiController {

    private final LottoApiService lottoApiService;

    @GetMapping("/lotto/loop")
    public void getLotto() throws Exception{

        /*{"totSellamnt":3681782000
                ,"returnValue":"success"
                ,"drwNoDate":"2002-12-07"
                ,"firstWinamnt":0
                ,"drwtNo6":40
                ,"drwtNo4":33
                ,"firstPrzwnerCo":0
                ,"drwtNo5":37
                ,"bnusNo":16
                ,"firstAccumamnt":863604600
                ,"drwNo":1
                ,"drwtNo2":23
                ,"drwtNo3":29
                ,"drwtNo1":10}*/

        // 1.Parse Pretty
        int loop = 1022;
        for(int i = 1; i<=loop; i++){
            String json = lottoApiService.readUrl("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+i);
            Gson gson = new Gson();
            Lotto lotto = gson.fromJson(json, Lotto.class);

            //디비 등록
            lottoApiService.lottoNumSave(lotto);
            log.info("lotto controller: "+lotto.toString());
        }


    }
}
