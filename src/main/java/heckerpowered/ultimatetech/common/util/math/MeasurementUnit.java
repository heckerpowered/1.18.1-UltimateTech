package heckerpowered.ultimatetech.common.util.math;

public enum MeasurementUnit {
    FEMTO("Femto", "f", 0.000000000000001D),
    PICO("Pico", "p", 0.000000000001D),
    NANO("Nano", "n", 0.000000001D),
    MICRO("Micro", "\u00B5", 0.000001D),
    MILLI("Milli", "m", 0.001D),
    BASE("", "", 1),
    KILO("Kilo", "k", 1_000D),
    MEGA("Mega", "M", 1_000_000D),
    GIGA("Giga", "G", 1_000_000_000D),
    TERA("Tera", "T", 1_000_000_000_000D),
    PETA("Peta", "P", 1_000_000_000_000_000D),
    EXA("Exa", "E", 1_000_000_000_000_000_000D),
    ZETTA("Zetta", "Z", 1_000_000_000_000_000_000_000D),
    YOTTA("Yotta", "Y", 1_000_000_000_000_000_000_000_000D);

    /**
     * long name for the unit
     */
    private final String name;

    /**
     * short unit version of the unit
     */
    private final String symbol;

    /**
     * Point by which a number is considered to be of this unit
     */
    private final double value;

    MeasurementUnit(String name, String symbol, double value) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
    }

    public String getName(boolean getShort) {
        if (getShort) {
            return symbol;
        }

        return name;
    }

    public double getValue() {
        return value;
    }

    public double process(double d) {
        return d / value;
    }

    public boolean above(double d) {
        return d > value;
    }

    public boolean below(double d) {
        return d < value;
    }
}
