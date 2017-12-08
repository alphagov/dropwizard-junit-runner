# Dropwizard JUnit Runner
> JUnit Runner implementation that starts up [DropwizardTestSupport](http://www.dropwizard.io/1.2.0/docs/manual/testing.html) 
> applications globally based on the provided configuration

## Usage example

```java
@RunWith(DropwizardJunitRunner.class)
@DropwizardConfig(app = MyApp.class, config = configClasspathLocation)
public class MyClassTest {
    
    @DropwizardPortValue
    private int port;

    @Test
    public void exampleTestThatExpectsADropwizardAppRunningBeforeExecuted() throws Exception {
        
        given().get(create("http://localhost:" + port + "/test"))
               .then()
               .statusCode(200);
    }
}
```
## Release History
* 0.0.1
    * Work in progress

## Meta

* GOV.UK Pay
    * raul.garcia@digital.cabinet-office.gov.uk
    *  
    
Distributed under the MIT license. See ``LICENSE`` for more information.

[Goverment Digital Service](https://github.com/alphagov)

## Contributing

1. Fork it (<https://github.com/alphagov/dropwizard-junit-runner/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
