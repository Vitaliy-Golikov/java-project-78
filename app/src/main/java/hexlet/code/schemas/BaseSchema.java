package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> check = new LinkedHashMap<>();
    protected boolean isRequired;

    public abstract BaseSchema<T> required();

    public final void setRequired() {
        isRequired = true;
    }

    public final boolean isValid(T data) {
        if (data == null) {
            return !isRequired;
        }

        for (Map.Entry<String, Predicate<T>> valid : check.entrySet()) {
            if (!valid.getValue().test(data)) {
                return false;
            }
        }
        return true;
    }

    protected final void addCheck(String toAddName, Predicate<T> toAdd) {
        check.put(toAddName, toAdd);
    }
}
