package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas")
    public Response allBookings(){
        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking"); //https://treinamento-api.herokuapp.com/booking
    }
    @Step("Buscar uma reserva")
    public Response oneBooking(Integer id){
        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking/"+ id); //https://treinamento-api.herokuapp.com/booking/id
    }
}
