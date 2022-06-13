package lotto.practice.random.service;

import lotto.practice.random.domain.Pay;
import lotto.practice.random.repository.LottoMemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LottoService {

    //필드
    private final static int PRICE = 1000; //lotto 금액
    private final LottoMemoRepository lottoMemoRepository;
    private Pay pay;


    //생성자
    @Autowired
    public LottoService(LottoMemoRepository lottoMemoRepository) {
        this.lottoMemoRepository = lottoMemoRepository;
    }

    //메소드
    public String buyResult(int buying){
        if(buying == 0 || buying/PRICE != 0){
            return "❗️1,000원 단위로 입력해주세요❗️";
        }else{
            return (buying/PRICE)+"개를 구입했습니다 :-) ";
        }
    }

}
