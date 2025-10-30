package co.edu.hotel.inventarioservice.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",        // Ruta de los features
        glue = {
                "co.edu.hotel.inventarioservice.config", //Paquete config
                "co.edu.hotel.inventarioservice.steps" // Paquete de los steps
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        tags = ""
)
public class CucumberRunnerTest {
}
