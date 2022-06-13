package lotto.practice.random.domain;

import java.security.PrivateKey;

public class Pay {

    //필드
    private int buying;
    private int buyNum;

    private String type;


    //getter, setter
    public int getBuying() {
        return buying;
    }

    public void setBuying(int buying) {
        this.buying = buying;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //일반 메소드
    // - 번호 출력 타입
    public String getTypeOut(String type){

        String typeOut = "";

        switch (type){
            case "allAuto" :
                typeOut = "allAuto";
            break;
            case "selectNum" :
                typeOut = "selectNum";
            break;
            case "allSelect" :
                typeOut = "allSelect";
            break;
        }

        return typeOut;
    }

}
