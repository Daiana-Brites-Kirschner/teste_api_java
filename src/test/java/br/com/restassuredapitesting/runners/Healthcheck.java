package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.healthcheck.tests.GetHealthcheckTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.Healthcheck.class)
@Suite.SuiteClasses({
        GetHealthcheckTest.class,
})
public class Healthcheck {
}
