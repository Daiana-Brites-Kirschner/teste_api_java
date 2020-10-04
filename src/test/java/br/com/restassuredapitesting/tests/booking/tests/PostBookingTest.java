package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class PostBookingTest extends BaseTest {
PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void criarNovaReserva() throws Exception {
        postBookingRequest.criarUmBooking()
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void erro500PayloadResrvaInvalida() throws Exception {
        postBookingRequest.criarBookingPayloadReservaInvalida()
                .then()
                .statusCode(500)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(equalTo("Internal Server Error"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void criarNovaReservaComMaisParametros() throws Exception {
        postBookingRequest.criarUmBookingComMaisParametros()
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void validarStatusCode418() throws Exception {
        postBookingRequest.criarBookingComAcceptInvalido()
                .then()
                .statusCode(418)
                .time(lessThan(5L), TimeUnit.SECONDS)
                .body(equalTo("I'm a teapot"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de mais de um livro em sequencia")
    public void criarMaisDeUmaReserva() throws Exception {
        postBookingRequest.criarUmBooking()
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);
        postBookingRequest.criarUmBooking()
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);
        postBookingRequest.criarUmBooking()
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);
    }

}
