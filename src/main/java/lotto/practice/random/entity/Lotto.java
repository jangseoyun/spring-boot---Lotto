package lotto.practice.random.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "t_lotto")
public class Lotto {
    /**
     * 각 회차별 로또 정보(데이터)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotto_no")
    private Long no;

    @Column(name = "six_ball")
    private String sixBall;

    /*번호 추출*/
    @Column(name = "ball_01")
    private int ball01;

    @Column(name = "ball_02")
    private int ball02;

    @Column(name = "ball_03")
    private int ball03;

    @Column(name = "ball_04")
    private int ball04;

    @Column(name = "ball_05")
    private int ball05;

    @Column(name = "ball_06")
    private int ball06;

    @Column(name = "ball_bonus")
    private int ball_bonus;
    /*번호 추출*/

    @Column(name = "lotto_date")
    private LocalDateTime lottoDate; //추첨일

    @Column(name="tot_sellamnt")
    private Long totSellamnt;       //누적 상금

    @Column(name="first_winamnt")
    private Long firstWinamnt;      //1등 당첨액

    @Column(name="first_przwner_co")
    private Long firstPrzwnerCo;    //1등 당첨인원

    @Column(name="first_accumamnt")
    private Long firstAccumamnt;    //1등 총액

    @Column(name = "drw_no")
    private Long drwNo; //로또 회차

}
