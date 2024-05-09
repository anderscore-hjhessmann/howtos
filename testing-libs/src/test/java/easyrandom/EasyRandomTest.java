package easyrandom;

import com.github.javafaker.Faker;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jeasy.random.FieldPredicates.named;

public class EasyRandomTest {

    /**
     * @see <a href="https://dzone.com/articles/improving-unit-test-maintainability">...</a>
     */
    @Test
    void simple() {
        EasyRandom generator = new EasyRandom();
        var person = generator.nextObject(Person.class);
        System.out.println("Person: " + person);

        List<Person> persons = generator.objects(Person.class, 5).toList();
        assertThat(persons).hasSize(5);
    }

    @Test
    void complex() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(Address.class, this::createRandomAddress)
                .randomize(named("age"), () -> Faker.instance().number().numberBetween(5, 25))
                .collectionSizeRange(2, 5)
                .stringLengthRange(3, 8);
        EasyRandom generator = new EasyRandom(parameters);
        var person = generator.nextObject(Person.class);
        System.out.println("Person: " + person);
    }

    private Address createRandomAddress() {
        var address = new Address();
        address.setCity("San Francisco");
        address.setStreet("Random street");
        return address;
    }
}
