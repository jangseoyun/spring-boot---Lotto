package lotto.practice.random.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_lotto")
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotto_no")
    private Long id;

    @Column(name = "six_ball")
    private String sixBall;

    @Column(name = "ball_01")
    private int ball01;
    @Column(name = "ball_02")
    private int ball02;

    private LocalDate date;         //추첨일

    @Column(name="tot_sellamnt")
    private long totSellamnt;       //총상금액

    @Column(name="first_winamnt")
    private long firstWinamnt;      //1등 당첨액

    @Column(name="first_przwner_co")
    private long firstPrzwnerCo;    //1등 당첨인원

    @Column(name="first_accumamnt")
    private long firstAccumamnt;    //1등 총액


       /* "totSellamnt": 103727042000,
                "returnValue": "success",
                "drwNoDate": "2022-03-26",**

                "drwtNo6": 44,**
                "drwtNo4": 31,**
                "firstPrzwnerCo": 11,
                "drwtNo5": 41,**
                "bnusNo": 33,**

                "drwNo": 1008,**
                "drwtNo2": 11,**
                "drwtNo3": 30,**
                "drwtNo1": 9*/

}
