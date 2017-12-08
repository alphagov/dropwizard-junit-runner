package uk.gov.dropwizard.junit;

public final class DropwizardJUnitRunnerException extends RuntimeException {

    DropwizardJUnitRunnerException(String message) {
        super(message);
    }

    DropwizardJUnitRunnerException(Exception exception) {
        super(exception);
    }
}
