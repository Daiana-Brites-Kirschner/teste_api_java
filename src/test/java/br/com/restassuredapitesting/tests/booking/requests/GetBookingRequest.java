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
    @Step("Filtro de reservas pelo primeiro nome")
    public Response listarFiltroFirstname(String firstname) {
        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking?firstname="+ firstname); //https://treinamento-api.herokuapp.com/booking?firstname=sally
    }
    @Step("Filtro de reservas pelo sobrenome")
    public Response filtroLastname(String lastname) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?lastname=" + lastname);
    }
    @Step("Filtro de reservas pelo checkin")
    public Response filtroCheckin(String checkin) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=" + checkin);
    }
    @Step("Filtro de reservas pelo checkout")
    public Response filtroCheckout(String checkout) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkout=" + checkout);
    }
    @Step("Filtro de reservas com checkin e checkout")
    public Response filtroChekinCheckout(String checkin, String checkout) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin="+checkin+"&checkout="+checkout);
    }
    @Step("Filtro de reservas com primeiro nome, checkin e checkout")
    public Response filtroFirstnameCheckinCheckout(String firstname ,String checkin, String checkout) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname="+firstname+"&checkin="+checkin+"&checkout="+checkout);
    }
}
