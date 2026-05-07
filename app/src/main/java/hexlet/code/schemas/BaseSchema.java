package hexlet.code.schemas;

public abstract class BaseSchema<T, SELF extends BaseSchema<T, SELF>> {
    protected boolean required = false;

    public SELF required() {
        this.required = true;
        return (SELF) this;
    }

    public abstract boolean isValid(T value);

    // Вспомогательный метод для тестов
    public boolean check(T value) {
        return isValid(value);
    }
}