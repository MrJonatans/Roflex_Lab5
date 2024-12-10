import org.reflex.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class InjectorTest {
    @Test
    void testInjection() throws Exception {
        Injector injector = new Injector("config.properties");
        SomeBean bean = injector.inject(new SomeBean());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        bean.foo();

        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("A"));
        assertTrue(output.contains("C"));
    }

    @Test
    void testInjection_2() throws Exception {
        Injector injector = new Injector("config_2.properties");
        SomeBean bean = injector.inject(new SomeBean());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        bean.foo();

        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("B"));
        assertTrue(output.contains("C"));
    }

}
