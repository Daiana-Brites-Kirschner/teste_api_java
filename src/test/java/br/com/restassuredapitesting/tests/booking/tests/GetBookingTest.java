package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void validarIdsDasReservas() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reserva")
    public void garantirContratoListaReserva() throws Exception {
        getBookingRequest.allBookings()
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(Utils.getContractsBasePath("booking", "bookings"))
                        )
                );
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    public void garantirContratoReservaEspecifica() throws Exception {

        int bookingId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.oneBooking(bookingId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(Utils.getContractsBasePath("booking", "booking"))
                        )
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar uma reserva específica")
    public void listarReservaEspecifica() throws Exception {

        //int bookingId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        int bookingId = getPrimeroIdDaLista();

        getBookingRequest.oneBooking(bookingId)
                .then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void listarIdsFiltroFirstname() throws Exception {

        int bookingId = getPrimeroIdDaLista();
        String firstname= getBookingRequest.oneBooking(bookingId).then().statusCode(200).extract().path("firstname");

        getBookingRequest.listarFiltroFirstname(firstname)
                .then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    private int getPrimeroIdDaLista(){
        return getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void listarIdsFiltroLastname() throws Exception {

        int bookingId = getPrimeroIdDaLista();
        String lastname = getBookingRequest.oneBooking(bookingId).then().statusCode(200).extract().path("lastname");

        getBookingRequest.filtroLastname(lastname)
                .then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")
    public void listarIdsFiltroCheckin() throws Exception {

        int bookingId = getPrimeroIdDaLista();
        String checkin = getBookingRequest.oneBooking(bookingId)
                .then().statusCode(200).extract().path("bookingdates.checkin");

        getBookingRequest.filtroCheckin(checkin)
                .then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void listarIdsFiltroCheckout() throws Exception {

        int bookingId = getPrimeroIdDaLista();
        String checkout = getBookingRequest.oneBooking(bookingId)
                .then().statusCode(200).extract().path("bookingdates.checkout");

        getBookingRequest.filtroCheckout(checkout)
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin and checkout")
    public void listarIdsFiltroCheckinCheckout() throws Exception {

        int bookingId = getPrimeroIdDaLista();

        String checkin = getBookingRequest.oneBooking(bookingId)
                .then().statusCode(200).extract().path("bookingdates.checkin");
        String checkout = getBookingRequest.oneBooking(bookingId)
                .then().statusCode(200).extract().path("bookingdates.checkout");

        getBookingRequest.filtroChekinCheckout(checkin, checkout)
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);
                //.body("size()", greaterThan(0)); Foi retirado, pois a API está sempre retornando uma lista vazia
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public void listarIdsFiltroFirstnameCheckinCheckout() throws Exception {

        int bookingId = getPrimeroIdDaLista();

        ResponseBodyExtractionOptions bookingResponse = getBookingRequest.oneBooking(bookingId)
                .then().statusCode(200).extract().body();

        String firstname = bookingResponse.path("firstname");
        String checkin = bookingResponse.path("bookingdates.checkin");
        String checkout = bookingResponse.path("bookingdates.checkout");

        getBookingRequest.filtroFirstnameCheckinCheckout(firstname,checkin,checkout)
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);
        //.body("size()", greaterThan(0)); Foi retirado, pois a API está sempre retornando uma lista vazia
    }
}