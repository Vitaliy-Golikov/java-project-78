package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTest {
    private Validator validator;
    private MapSchema<String> mapSchema;
    private Map<String, String> data;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
        mapSchema = validator.map();
        data = new HashMap<>();
        data.put("key", "value");
    }

    @Test
    public void testValidatorMap() {
        MapSchema<String> schema = validator.map();
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void testRequiredNull() {
        assertTrue(mapSchema.isValid(null));
    }

    @Test
    public void testRequiredPositive() {
        mapSchema.required();
        assertTrue(mapSchema.isValid(data));
    }

    @Test
    public void testRequiredNegative() {
        mapSchema.required();
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void testSizeOf() {
        mapSchema.sizeof(2);
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }

    @Test
    public void testSizeOfNegative() {
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));
    }

    @Test
    public void testSizeOfNullWithoutRequired() {
        mapSchema.sizeof(2);
        assertTrue(mapSchema.isValid(null));
    }

    @Test
    public void testSizeOfNullWithRequired() {
        mapSchema.required();
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void testShape() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertTrue(mapSchema.isValid(human));
    }

    @Test
    public void testShapeNegative() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "B");

        assertFalse(mapSchema.isValid(human));
    }

    @Test
    public void testShapeNullField() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());

        mapSchema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", null);

        assertFalse(mapSchema.isValid(human));
    }

    @Test
    public void testShapeMissingKey() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());

        mapSchema.shape(schemas);

        Map<String, String> human = new HashMap<>();

        assertFalse(mapSchema.isValid(human));
    }

    @Test
    public void testShapeNullWithoutRequired() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());

        mapSchema.shape(schemas);

        assertTrue(mapSchema.isValid(null));
    }

    @Test
    public void testShapeNullWithRequired() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());

        mapSchema.required();
        mapSchema.shape(schemas);

        assertFalse(mapSchema.isValid(null));
    }

    @Test
    public void testShapeWithNumberSchema() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().required().positive());

        MapSchema<Object> schema = validator.map();
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", 20);

        assertTrue(schema.isValid(human));
    }

    @Test
    public void testShapeWithInvalidNumberSchema() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().required().positive());

        MapSchema<Object> schema = validator.map();
        schema.shape(schemas);

        Map<String, Object> human = new HashMap<>();
        human.put("name", "John");
        human.put("age", -5);

        assertFalse(schema.isValid(human));
    }
}