package hexlet.code;

public class StringSchema extends BaseSchema<String, StringSchema> {
    private Integer minLength = null;
    private String contains = null;

    public StringSchema minLength(Integer num) {
        this.minLength = num;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(String string) {

        if (required) {
            if (string == null || string.equals("")) {
                return false;
            }
        }

        if (string != null) {
            if (minLength != null) {
                if (string.length() < minLength) {
                    return false;
                }
            }

            if (contains != null) {
                if (!string.contains(contains)) {
                    return false;
                }
            }
        }

        return true;
    }

}
