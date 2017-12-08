package uk.gov.dropwizard.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.gov.dropwizard.app.MyApp;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.net.URI.create;

@RunWith(DropwizardJUnitRunner.class)
@DropwizardConfig(app = MyApp.class, config = "config/test-config.yaml")
public class DropwizardJUnitRunner1Test {

    @DropwizardPortValue
    private int port;

    private URI myAppTestResourceUri;

    @Before
    public void setup() {
        myAppTestResourceUri = create(format("http://localhost:%d/foo", port));
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationInAnotherClass() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationAnotherTestInSameAnotherClass() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationInAnotherClass1() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationAnotherTestInSameAnotherClass1() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationInAnotherClass2() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFooResourceFromARunningApplicationAnotherTestInSameAnotherClass2() throws Exception {
        given().get(myAppTestResourceUri)
                .then()
                .statusCode(200);
    }
}
