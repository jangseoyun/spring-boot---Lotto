package lotto.practice.random.repository;

import java.util.Objects;

public class InputVo {

    //사용자가 요청한 타입, 구입금액

    //필드
    private String type;
    private int buying;

    //생성자
    public InputVo(){}
    public InputVo(String type, int buying) {
        this.type = type;
        this.buying = buying;
    }

    //getter, setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBuying() {
        return buying;
    }

    public void setBuying(int buying) {
        this.buying = buying;
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

    @Override
    public String toString() {
        return "InputVo{" +
                "type='" + type + '\'' +
                ", buying=" + buying +
                '}';
    }
}
