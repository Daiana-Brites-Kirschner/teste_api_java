package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token")
    public void alterarUmaReservaUtilizandoToken() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroId, Utils.validaPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void alterarUmaReservaUtilizandoBasicAuth() throws Exception {
        int segundoId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[1].bookingid");

        putBookingRequest.alterarUmaReservaBasicAuth(segundoId, Utils.validaPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void alterarUmaReservaSemToken() throws Exception {
            int id = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

            putBookingRequest.alterarUmaReservaSemToken(id, Utils.validaPayloadBooking()).then()
                    .statusCode(403)
                    .time(lessThan(2L), TimeUnit.SECONDS)
                    .body(equalTo("Forbidden"));
        }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Alterar uma reserva quando o token enviado for inválido")
    public void alterarUmaReservaTokenInvalido() throws Exception {
        int id = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComTokenInvalido(id, Utils.validaPayloadBooking()).then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body(equalTo("Forbidden"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Alterar uma reserva que não existe")
    public void alterarUmaReservaQueNaoExiste() throws Exception {
        int id = 0;
        putBookingRequest.alterarUmaReservaComToken(id, Utils.validaPayloadBooking()).then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body(equalTo("Method Not Allowed"));
    }
}
