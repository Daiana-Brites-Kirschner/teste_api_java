package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Condition;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaComToken(int id, JSONObject payload){
        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }
    @Step("Alterar uma reserva usando o Basic auth")
    public Response alterarUmaReservaBasicAuth(int id, JSONObject payload) {
        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("authorization",postAuthRequest.getBasicAuth())
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva sem token")
    public Response alterarUmaReservaSemToken(int id, JSONObject payload) {
            return given()
                    .header("Content-Type","application/json")
                    .header("Accept","application/json")
                    .when()
                    .body(payload.toString())
                    .put("booking/" + id);
        }
    @Step("Alterar uma reserva quando o token enviado for inválido")
    public Response alterarUmaReservaComTokenInvalido(int id, JSONObject payload) {
            return given()
                    .header("Content-Type","application/json")
                    .header("Accept","application/json")
                    .header("Cookie","token=1234invalido")
                    .when()
                    .body(payload.toString())
                    .put("booking/" + id);
    }
}
