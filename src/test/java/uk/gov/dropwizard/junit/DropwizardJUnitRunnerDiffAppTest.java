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
@DropwizardConfig(app = MyApp2.class, config = "config/test-config.yaml")
public class DropwizardJUnitRunnerDiffAppTest {

    @DropwizardPortValue
    private int port;

    private URI myAppTestResourceUri;

    @Before
    public void setup() {
        myAppTestResourceUri = create(format("http://localhost:%d/foo", port));
    }

    @Test
    public void shouldGetFooResourceFromARunningApplication() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationAnotherTestInSameTestClass() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }
}
