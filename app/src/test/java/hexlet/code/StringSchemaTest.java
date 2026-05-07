package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringSchemaTest {

    @Test
    void testStringSchemaWithoutRequired() {
        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testStringSchemaWithRequired() {
        var v = new Validator();
        var schema = v.string().required();


        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testStringSchemaWithMinLength() {
        var v = new Validator();
        var schema = v.string().minLength(5);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(" "));
        assertFalse(schema.isValid("word"));
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("helllo"));
    }

    @Test
    void testStringSchemaWithContains() {
        var v = new Validator();
        var schema = v.string().contains("el");

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(" "));
        assertFalse(schema.isValid("word"));
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("helllo"));
        assertTrue(schema.isValid(" elongated"));
        assertTrue(schema.isValid("el"));
    }

    @Test
    void testStringSchemaChain() {
        var v = new Validator();
        var schema = v.string().required().minLength(5).contains("el");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(" "));
        assertFalse(schema.isValid("word"));
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("helllo"));
        assertTrue(schema.isValid(" elongated"));
        assertFalse(schema.isValid("el"));
    }
}
