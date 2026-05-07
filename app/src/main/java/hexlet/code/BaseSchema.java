package hexlet.code;

public abstract class BaseSchema<T, SELF extends BaseSchema<T, SELF>> {
    protected boolean required = false;

    public SELF required() {
        this.required = true;
        return (SELF) this;
    }

    public abstract boolean isValid(T value);
}
