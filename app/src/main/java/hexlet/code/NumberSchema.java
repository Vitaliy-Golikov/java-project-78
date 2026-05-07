package hexlet.code;

public class NumberSchema extends BaseSchema<Integer, NumberSchema> {
    private boolean positive = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int num1, int num2) {
        this.minRange = num1;
        this.maxRange = num2;
        return this;
    }

    @Override
    public boolean isValid(Integer num) {
        if (required) {
            if (num == null) {
                return false;
            }
        }

        if (num == null) {
            return true;
        }

        if (positive) {
            if (num <= 0) {
                return false;
            }
        }

        if (minRange != null && maxRange != null) {
            if (minRange > num || num > maxRange) {
                return false;
            }
        }

        return true;
    }
}
