package lotto.practice.random.domain.lottoapi;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 각 회차별 로또 당첨 정보(데이터)
 */
@Getter
@Setter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "t_lotto_api")
public class LottoApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotto_no")
    private Long no;


    @Column(name = "six_ball")
    private String sixBall;

    /*번호 추출*/
    @Column(name = "ball_01")
    private int drwtNo1;

    @Column(name = "ball_02")
    private int drwtNo2;

    @Column(name = "ball_03")
    private int drwtNo3;

    @Column(name = "ball_04")
    private int drwtNo4;

    @Column(name = "ball_05")
    private int drwtNo5;

    @Column(name = "ball_06")
    private int drwtNo6;

    @Column(name = "ball_bonus")
    private int bnusNo;

    /*번호 추출*/
    @Column(name = "lotto_date")
    private String drwNoDate; //추첨일

    @Column(name = "tot_sellamnt")
    private Long totSellamnt;       //총 판매액

    @Column(name = "first_winamnt")
    private Long firstWinamnt;      //1게임당 당첨금액

    @Column(name="first_przwner_co")
    private Long firstPrzwnerCo;    //1등 당첨인원

    @Column(name = "first_accumamnt")
    private Long firstAccumamnt;    //등위별 총 당첨금액

    @Column(name = "drw_no")
    private Long drwNo; //로또 회차

    /**
     * 생성 메서드
     */

    /*    {"totSellamnt":3681782000
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

    public static LottoApi createLotto(LottoApi lotto){
        LottoApi entity = new LottoApi();
        entity.setTotSellamnt(lotto.getTotSellamnt());
        entity.setFirstPrzwnerCo(lotto.getFirstPrzwnerCo());
        entity.setFirstWinamnt(lotto.getFirstWinamnt());
        entity.setFirstAccumamnt(lotto.getFirstAccumamnt());

        entity.setDrwtNo1(lotto.getDrwtNo1());
        entity.setDrwtNo2(lotto.getDrwtNo2());
        entity.setDrwtNo3(lotto.getDrwtNo3());
        entity.setDrwtNo4(lotto.getDrwtNo4());
        entity.setDrwtNo5(lotto.getDrwtNo5());
        entity.setDrwtNo6(lotto.getDrwtNo6());

        entity.setSixBall(
                lotto.getDrwtNo1()+","
                +lotto.getDrwtNo2()+","
                +lotto.getDrwtNo3()+","
                +lotto.getDrwtNo4()+","
                +lotto.getDrwtNo5()+","
                +lotto.getDrwtNo6()
        );

        entity.setBnusNo(lotto.getBnusNo());
        entity.setDrwNo(lotto.getDrwNo());
        entity.setNo(lotto.getNo());
        entity.setDrwNoDate(lotto.getDrwNoDate());

        log.info("entity: "+entity);

        return entity;
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "no=" + no +
                ", sixBall=" + drwtNo1+","+drwtNo2+","+drwtNo3+","+drwtNo4+","+drwtNo5+","+drwtNo6+
                ", drwtNo1=" + drwtNo1 +
                ", drwtNo2=" + drwtNo2 +
                ", drwtNo3=" + drwtNo3 +
                ", drwtNo4=" + drwtNo4 +
                ", drwtNo5=" + drwtNo5 +
                ", drwtNo6=" + drwtNo6 +
                ", bnusNo=" + bnusNo +
                ", drwNoDate=" + drwNoDate +
                ", totSellamnt=" + totSellamnt +
                ", firstWinamnt=" + firstWinamnt +
                ", firstPrzwnerCo=" + firstPrzwnerCo +
                ", firstAccumamnt=" + firstAccumamnt +
                ", drwNo=" + drwNo +
                '}';
    }

}
