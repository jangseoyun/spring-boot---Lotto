package lotto.practice.random.service;

import lotto.practice.random.domain.Machine;
import lotto.practice.random.repository.InputVo;
import lotto.practice.random.repository.LottoMemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LottoService {

    //필드
    private final LottoMemoRepository lottoMemoRepository;
    private InputVo inputVo;
    Map<String, Object> resultBall = new HashMap<>();
    //domain
    private Machine machine;


    //생성자
    @Autowired
    public LottoService(LottoMemoRepository lottoMemoRepository) {
        this.lottoMemoRepository = lottoMemoRepository;
    }

    //메소드
    //2-1. 추출한 번호 map에 넣기
    public int ResultBall(InputVo inputVo){

        System.out.println("service buyNum 접속");
        System.out.println("inputVo = " + inputVo);

        //비즈니스 로직 domain에 요청하여 응답 -> map
        //구입한 갯수
        int buyNum = machine.buyNum(inputVo.getBuying());
        resultBall.put("buyNum",buyNum);
        //

        return 0;
    }

}
