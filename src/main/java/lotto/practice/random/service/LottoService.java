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
    //번호 추출 타입
    public String buyNum(String type){

        System.out.println("service buyNum 접속");
        System.out.println("type = " + type);

        String result = pay.getTypeOut(type);
        System.out.println("Service buyNum : "+result);

        return "";
    }

    //랜덤 번호 추출
    /*public String giveRandomNum(Pay pay){
        if(pay.get == 0 || buying/PRICE != 0){
            return "❗️1,000원 단위로 입력해주세요❗️";
        }else{
            return (buying/PRICE)+"개를 구입했습니다 :-) ";
        }
    }*/

}
