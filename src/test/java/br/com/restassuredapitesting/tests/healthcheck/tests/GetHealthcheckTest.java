package br.com.restassuredapitesting.tests.healthcheck.tests;

import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.healthcheck.requests.GetHealthcheckRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

public class GetHealthcheckTest extends BaseTest {

        GetHealthcheckRequest getHealthcheckRequest = new GetHealthcheckRequest();

        @Test
        @Severity(SeverityLevel.BLOCKER)
        @Category(Healthcheck.class)
        @DisplayName("Verificar se API está online")
        public void verificarHelthcheck() throws Exception{
            getHealthcheckRequest.verifyApiOnline().then()
                    .statusCode(201)
                    .time(lessThan(4L), TimeUnit.SECONDS)
                    .body(equalTo("Created"));
        }

}
