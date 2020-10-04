package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.auth.tests.PostAuthTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Condition;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Excluir um reserva com sucesso")
    public Response deleteOneBooking(int id){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie",postAuthRequest.getToken())
                .when()
                .delete("booking/"+id); //https://treinamento-api.herokuapp.com/booking/id
    }

    @Step("Excluir um reserva que não existe")
    public Response deletarUmaReservaQueNaoExiste(int id) {
        return given()
                .header("Content-Type","application/json")
                .header("Cookie",postAuthRequest.getToken())
                .when()
                .delete("booking/"+id);
    }

    @Step("Tentar excluir uma reserva sem autorização")
    public Response deletarUmaReservaSemAutorizacao (int id) {
        return given()
                .header("Content-Type","application/json")
                .when()
                .delete("booking/"+id);
    }
}
