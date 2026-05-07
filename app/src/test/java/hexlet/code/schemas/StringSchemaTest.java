package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringSchemaTest {
    private StringSchema stringSchema;

    @BeforeEach
    public void setUp() {
        stringSchema = new StringSchema();
    }
    @Test
    public void testRequiredNull() {
        assertTrue(stringSchema.isValid(null));
    }
    @Test
    public void testRequiredEmpty() {
        assertTrue(stringSchema.isValid(""));
    }
    @Test
    public void testRequiredPositive() {
        stringSchema.required();
        assertTrue(stringSchema.isValid("Hello"));
    }
    @Test
    public void testRequiredNegative() {
        stringSchema.required();
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
    }
    @Test
    public void testMinLength() {
        stringSchema.minLength(5);
        assertTrue(stringSchema.isValid("Hello"));
        assertTrue(stringSchema.isValid("Welcome"));
    }
    @Test
    public void testMinLengthNegative() {
        stringSchema.minLength(7);
        assertFalse(stringSchema.isValid("Hello"));
        assertFalse(stringSchema.isValid("Java"));
    }
    @Test
    public void testContains() {
        stringSchema.contains("llo");
        assertTrue(stringSchema.isValid("Hello"));
    }
    @Test
    public void testContainsNegative() {
        stringSchema.contains("zxc");
        assertFalse(stringSchema.isValid("Hello"));
    }
}
