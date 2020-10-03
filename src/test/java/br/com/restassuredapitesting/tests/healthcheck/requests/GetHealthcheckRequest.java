package br.com.restassuredapitesting.tests.healthcheck.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetHealthcheckRequest {
    @Step("Verificar se API está online")
    public Response verifyApiOnline(){
        return given()
                .when()
                .get("ping"); //https://treinamento-api.herokuapp.com/ping
    }
}
