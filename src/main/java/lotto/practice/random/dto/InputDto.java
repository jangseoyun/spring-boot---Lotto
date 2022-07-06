package lotto.practice.random.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InputDto {

    //사용자가 요청한 타입, 구입금액

    //필드
    private String numInput;
    private String type;
    private int buying;

    public InputDto(String type, int buying) {
        this.type = type;
        this.buying = buying;
    }

    //getter, setter

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
