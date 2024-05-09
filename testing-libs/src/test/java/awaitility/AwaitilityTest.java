package awaitility;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class AwaitilityTest {

    private final AtomicBoolean done = new AtomicBoolean();

    /**
     * @see <a href="https://github.com/awaitility/awaitility/wiki/Usage">...</a>
     */
    @Test
    void simple() {
        long start = System.nanoTime();
        delay();

        await().atMost(3, TimeUnit.SECONDS).until(() -> done.get());
        assertThat(System.nanoTime() - start).isGreaterThan((long)2e9);
    }

    @Test
    void assertJ() {
        delay();

        await().untilAsserted(() -> assertThat(done.get()).isTrue());
    }

    private void delay() {
        Thread.ofVirtual().start(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                done.set(true);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
