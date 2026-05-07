package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
