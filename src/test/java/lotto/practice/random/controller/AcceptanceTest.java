package lotto.practice.random.controller;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void environmentSetUp() {
        RestAssured.port = port;
    }

    @Test
    public void request() {
        //given
        Map param = new HashMap<>();

        param.put("userNo", 1);
        param.put("lottotype", "ALLAUTO");

        //when
        ExtractableResponse<Response> response = getRequest("/machine/lottoNum", param);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
    }

    public static ExtractableResponse<Response> getRequest(String path, Map<String, Object> param) {
        return RestAssured
                .given().log().all().queryParams(param).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(path)
                .then().log().all()
                .extract();
    }

    public static <T> ExtractableResponse<Response> postRequest(String path, T param) {
        return RestAssured
                .given().log().all().body(param).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(path)
                .then().log().all()
                .extract();
    }

}
