package uk.gov.dropwizard.junit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.stream;

/**
 * Runs a Dropwizard application with the given {@link DropwizardConfig} before the Test class if there is not an
 * application started yet (from a previous Test class using the same runner) with the same {@link DropwizardConfig}.
 *
 * Example:
 *
 * <pre>
 *  &#64;RunWith(DropwizardJUnitRunner.class)
 *  &#64;DropwizardConfig(app = MyApp.class, config = "config/test.yaml")
 *  public class MyTest {
 *
 *      &#64;Test
 *      public void shouldGetTestResourceFromARunningApplicationInAnotherClass() throws Exception {
 *          given().get("/test-resource")
 *                 .then()
 *                 .statusCode(200);
 *      }
 *  }
 * </pre>
 *
 * After this test and if more tests are being executed, the application will be kept alive so other tests that needs
 * similar configuration will benefit from the existing running application.
 */
public final class DropwizardJUnitRunner extends BlockJUnit4ClassRunner {

    public DropwizardJUnitRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Statement classBlock(final RunNotifier notifier) {
        DropwizardConfig dropwizardConfigAnnotation = dropwizardConfigAnnotation();
        DropwizardTestApplications.getOrCreate(dropwizardConfigAnnotation.app(), dropwizardConfigAnnotation.config());
        return super.classBlock(notifier);
    }

    @Override
    public Object createTest() throws Exception {
        Object testInstance = super.createTest();
        List<FrameworkField> annotatedFields = getTestClass().getAnnotatedFields();
        annotatedFields.forEach(frameworkField -> stream(frameworkField.getAnnotations())
                .filter(annotation -> annotation.annotationType().equals(DropwizardPortValue.class))
                .findFirst().ifPresent(injectPortValue(testInstance, frameworkField)));
        return testInstance;
    }

    private Consumer<Annotation> injectPortValue(Object testInstance, FrameworkField frameworkField) {
        return annotation -> {
            frameworkField.getField().setAccessible(true);
            try {
                DropwizardConfig dropwizardConfigAnnotation = dropwizardConfigAnnotation();
                frameworkField.getField().setInt(testInstance,
                        DropwizardTestApplications.getPort(dropwizardConfigAnnotation.app(), dropwizardConfigAnnotation.config()));
            } catch (IllegalAccessException e) {
                throw new DropwizardJUnitRunnerException(e);
            }
        };
    }

    private DropwizardConfig dropwizardConfigAnnotation() {
        return (DropwizardConfig) stream(getTestClass().getAnnotations())
                .filter(annotation -> annotation.annotationType().equals(DropwizardConfig.class))
                .findFirst()
                .orElseThrow(() -> new DropwizardJUnitRunnerException("DropwizardJUnitRunner requires annotation @DropwizardConfig to be present"));
    }
}
