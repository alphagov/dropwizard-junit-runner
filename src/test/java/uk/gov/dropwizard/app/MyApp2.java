package uk.gov.dropwizard.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MyApp2 extends Application<MyAppConfig> {

    @Override
    public void initialize(Bootstrap<MyAppConfig> bootstrap) { }

    @Override
    public void run(MyAppConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(new MyAppResource());
    }
}
