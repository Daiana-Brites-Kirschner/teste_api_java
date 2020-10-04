package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.Validatable;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Criar uma nova reserva ")
    public Response criarUmBooking(){
        JSONObject payload = new JSONObject();
        payload.put("firstname" , "Jim");
        payload.put("lastname" , "Brown");
        payload.put("totalprice" , 111);
        payload.put("depositpaid" , true);

        //Foi criado um objeto separado, pois bookingdates não é apenas um campo, ele é um objeto
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        payload.put("bookingdates",bookingdates);
        payload.put("additionalneeds" , "Breakfast");


        return given()
                .header("Content-Type","application/json")
                .when()
                .body(payload.toString())
                .post("booking");

    }

    @Step("Criar uma reserva com Payload  Inválido")
    public Response criarBookingPayloadReservaInvalida() {
        JSONObject payload = new JSONObject();
        payload.put("firstname" , 123456);
        payload.put("lastname" , "Brown");
        payload.put("totalprice" , 111);
        payload.put("depositpaid" , true);

        //Foi criado um objeto separado, pois bookingdates não é apenas um campo, ele é um objeto
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        payload.put("bookingdates",bookingdates);
        payload.put("additionalneeds", "Breakfast");

        return given()
                .header("Content-Type","application/json")
                .when()
                .body(payload.toString())
                .post("booking");

    }

    public Response criarUmBookingComMaisParametros() {
        JSONObject payload = new JSONObject();
        payload.put("firstname" , "Jim");
        payload.put("lastname" , "Brown");
        payload.put("totalprice" , 111);
        payload.put("depositpaid" , true);
        payload.put("teste", "parametro a mais");
        payload.put("casa",123);

        //Foi criado um objeto separado, pois bookingdates não é apenas um campo, ele é um objeto
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        payload.put("bookingdates",bookingdates);
        payload.put("additionalneeds" , "Breakfast");


        return given()
                .header("Content-Type","application/json")
                .when()
                .body(payload.toString())
                .post("booking");

    }

    public Response criarBookingComAcceptInvalido() {
        JSONObject payload = new JSONObject();
        payload.put("firstname" , "Jim");
        payload.put("lastname" , "Brown");
        payload.put("totalprice" , 111);
        payload.put("depositpaid" , true);

        //Foi criado um objeto separado, pois bookingdates não é apenas um campo, ele é um objeto
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        payload.put("bookingdates",bookingdates);
        payload.put("additionalneeds" , "Breakfast");


        return given()
                .header("Content-Type","application/json")
                .header("Accept","Error")
                .when()
                .body(payload.toString())
                .post("booking");
    }
}
