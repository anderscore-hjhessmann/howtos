package faker;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class FakerTest {

    /**
     * @see <a href="https://dzone.com/articles/improving-unit-test-maintainability">...</a>
     */
    @Test
    void simple() {
        System.out.printf("full name: %s%n", Faker.instance().name().fullName());
        System.out.printf("age: %d%n", Faker.instance().number().numberBetween(5, 25));
    }

    /**
     * @see <a href="https://www.baeldung.com/java-faker">...</a>
     */
    @Test
    void service() {
        FakeValuesService service = new FakeValuesService(Locale.GERMANY, new RandomService());

        System.out.printf("email: %s%n", service.bothify("????##@gmail.com"));
        System.out.printf("regex: %s%n", service.regexify("[a-z1-9]{10}"));
    }
}
