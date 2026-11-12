package Api;

import Common.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ApiSpecs {

    private static final String BASE_URL = "https://regoffice.senla.eu";

    private static String basicUser() {
        return Config.standUser();
    }

    private static String basicPassword() {
        return Config.standPassword();
    }

    private static final Logger restAssuredLog = LogManager.getLogger("Api.RestAssured");

    public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setAuth(RestAssured.preemptive().basic(basicUser(), basicPassword()))
            .setContentType(ContentType.JSON)
            .addHeader("Channel", "web")
            .addFilter(new RequestLoggingFilter(logStream()))
            .addFilter(new ResponseLoggingFilter(logStream()))
            .build();

    public static final ResponseSpecification RESPONSE_OK = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    private static PrintStream logStream() {
        return new PrintStream(new RestAssuredToLog4jStream(), false, StandardCharsets.UTF_8);
    }

    private static final class RestAssuredToLog4jStream extends OutputStream {

        private final ByteArrayOutputStream line = new ByteArrayOutputStream();

        @Override
        public void write(int b) {
            if (b == '\n') {
                if (line.size() > 0) {
                    restAssuredLog.debug(line.toString(StandardCharsets.UTF_8));
                    line.reset();
                }
            } else if (b != '\r') {
                line.write(b);
            }
        }
    }
}
