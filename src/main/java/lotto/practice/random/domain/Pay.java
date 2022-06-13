package lotto.practice.random.domain;

import java.security.PrivateKey;

public class Pay {

    //필드
    private String payInput;
    private int payNum;


    //getter, setter
    public String getPayInput() {
        return payInput;
    }

    public void setPayInput(String payInput) {
        this.payInput = payInput;
    }

    //구입한 로또 수 가져오기
    public int getPayNum(int buying) {
        return payNum;
    }

    public void setPayNum(int payNum) {
        this.payNum = payNum;
    }

    //메소드
}
