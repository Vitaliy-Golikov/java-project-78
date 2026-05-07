package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>, MapSchema> {
    private Integer sizeof = null;
    private Map<String, BaseSchema> schemas = null;

    public MapSchema sizeof(Integer num) {
        this.sizeof = num;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.schemas = schemas;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> map) {
        if (required) {
            if (map == null) {
                return false;
            }
        }

        if (map == null) {
            return true;
        }

        if (sizeof != null) {
            if (map == null || map.size() != sizeof) {
                return false;
            }
        }

        if (schemas != null) {
            for (var entry : schemas.entrySet()) {
                String key = entry.getKey();
                var schema = entry.getValue();

                if (!map.containsKey(key)) {
                    return false;
                }

                var valueMap = map.get(key);
                if(!schema.isValid(valueMap)) {
                    return false;
                }
            }
        }

        return true;
    }

}
