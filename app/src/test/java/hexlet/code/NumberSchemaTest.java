package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberSchemaTest {

    @Test
    void testNumberSchemaWithoutRequired() {
        var v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-1));
    }

    @Test
    void testNumberSchemaWithRequired() {
        var v = new Validator();
        var schema = v.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-1));
    }

    @Test
    void testNumberSchemaWithPositive() {
        var v = new Validator();
        var schema = v.number().positive();

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(1));
    }

    @Test
    void testNumberSchemaWithRange() {
        var v = new Validator();
        var schema = v.number().range(-7, 7);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-1));
        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(-7));
        assertTrue(schema.isValid(7));
        assertFalse(schema.isValid(-8));
        assertFalse(schema.isValid(8));
    }

    @Test
    void testNumberSchemaChain() {
        var v = new Validator();
        var schema = v.number().required().positive().range(-7, 7);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(1));
        assertFalse(schema.isValid(-7));
        assertTrue(schema.isValid(7));
        assertFalse(schema.isValid(-8));
        assertFalse(schema.isValid(8));
    }
}
