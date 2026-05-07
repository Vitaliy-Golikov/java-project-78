package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MapSchemaTest {

    @Test
    void testMapSchemaWithoutRequired() {
        var v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var map = new HashMap<>();
        map.put("key", "value");

        assertTrue(schema.isValid(map));
    }

    @Test
    void testMapSchemaWithRequired() {
        var v = new Validator();
        var schema = v.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var map = new HashMap<>();
        map.put("key", "value");

        assertTrue(schema.isValid(map));
    }

    @Test
    void testMapSchemaWithSizeof() {
        var v = new Validator();
        var schema = v.map().sizeof(3);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(new HashMap<>()));

        var map = new HashMap<>();
        map.put("key", "value");

        assertFalse(schema.isValid(map));

        var map2 = new HashMap<>();
        map2.put("key1", "value1");
        map2.put("key2", "value2");

        assertFalse(schema.isValid(map2));

        var map3 = new HashMap<>();
        map3.put("key1", "value1");
        map3.put("key2", "value2");
        map3.put("key3", "value3");

        assertTrue(schema.isValid(map3));

        var map4 = new HashMap<>();
        map4.put("key1", "value1");
        map4.put("key2", "value2");
        map4.put("key3", "value3");
        map4.put("key4", "value4");

        assertFalse(schema.isValid(map4));
    }


}
