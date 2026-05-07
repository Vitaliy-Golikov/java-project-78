package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema<T> extends BaseSchema<Map<String, ?>> {

    @Override
    public MapSchema<T> required() {
        setRequired();
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        addCheck("sizeof", value -> value.size() == size);
        return this;
    }

    public MapSchema<T> shape(Map<String, ? extends BaseSchema<?>> shapeMap) {
        addCheck("shape", value -> {
            for (var entry : shapeMap.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object fieldValue = value.get(key);

                if (!checkBySchema(schema, fieldValue)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @SuppressWarnings("unchecked")
    private boolean checkBySchema(BaseSchema<?> schema, Object value) {
        return ((BaseSchema<Object>) schema).isValid(value);
    }
}
