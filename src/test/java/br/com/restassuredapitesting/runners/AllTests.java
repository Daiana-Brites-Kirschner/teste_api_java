package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.DeleteBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.GetBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.PostBookingTest;
import br.com.restassuredapitesting.tests.booking.tests.PutBookingTest;
import br.com.restassuredapitesting.tests.healthcheck.tests.GetHealthcheckTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({
        br.com.restassuredapitesting.suites.Acceptance.class,
        br.com.restassuredapitesting.suites.Contract.class,
        br.com.restassuredapitesting.suites.E2e.class,
        br.com.restassuredapitesting.suites.Healthcheck.class
})
@Suite.SuiteClasses({
        DeleteBookingTest.class,
        GetBookingTest.class,
        PostBookingTest.class,
        PutBookingTest.class,
        GetHealthcheckTest.class
})

public class AllTests {
}
