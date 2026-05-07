package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    void testValidatorCreatesStringSchema() {
        var v = new Validator();
        var schema = v.string();

        assertNotNull(schema);
        assertInstanceOf(StringSchema.class, schema);
    }

    @Test
    void testValidatorCreatesNumberSchema() {
        var v = new Validator();
        var schema = v.number();

        assertNotNull(schema);
        assertInstanceOf(NumberSchema.class, schema);
    }

    @Test
    void testValidatorCreatesMapSchema() {
        var v = new Validator();
        var schema = v.map();

        assertNotNull(schema);
        assertInstanceOf(MapSchema.class, schema);
    }
}
