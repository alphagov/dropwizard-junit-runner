package uk.gov.dropwizard.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.gov.dropwizard.app.MyApp2;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.net.URI.create;

@RunWith(DropwizardJUnitRunner.class)
@DropwizardConfig(app = MyApp2.class, config = "config/test-config-2.yaml")
public class DropwizardJUnitRunnerDiffConfigTest {

    @DropwizardPortValue
    private int port;

    private URI myApp2TestResourceUri;

    @Before
    public void setup() {
        myApp2TestResourceUri = create(format("http://localhost:%d/foo", port));
    }

    @Test
    public void shouldGetFooResourceFromARunningApplication() throws Exception {
        given().get(myApp2TestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationAnotherTestInSameTestClass() throws Exception {
        given().get(myApp2TestResourceUri)
                .then()
                .statusCode(200);
    }
}
