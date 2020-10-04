package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

@Feature("Deletar Booking")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Excluir um id de reserva")
    public void excluirIddasReservas() throws Exception{

        int deletId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");//[0] Sempre irá chamar toda a lista e extrair o primeiro elemento do array

        deleteBookingRequest.deleteOneBooking(deletId).then()
                .statusCode(201)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body(equalTo("Created"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir um reserva que não existe")
    public void excluirReservaQueNaoExiste() throws Exception{

        int id = 0;

        deleteBookingRequest.deletarUmaReservaQueNaoExiste(id).then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body(equalTo("Method Not Allowed"));


    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir uma reserva sem autorização")
    public void excluirReservaSemAutorizacao() throws Exception{

        int id = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");//[0] Sempre irá chamar toda a lista e extrair o primeiro elemento do array


        deleteBookingRequest.deletarUmaReservaSemAutorizacao(id).then()
                .statusCode(403)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body(equalTo("Forbidden"));


    }

}
