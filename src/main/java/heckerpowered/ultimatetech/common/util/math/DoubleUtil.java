package heckerpowered.ultimatetech.common.util.math;

public final class DoubleUtil {
    private DoubleUtil() {
    }

    public static boolean isZero(double value) {
        return Math.abs(value) < 10.0 * Double.MIN_NORMAL;
    }
}
