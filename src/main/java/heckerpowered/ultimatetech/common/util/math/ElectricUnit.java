package heckerpowered.ultimatetech.common.util.math;

public enum ElectricUnit {
    JOULES("J");

    /**
     * short unit version of the unit
     */
    private final String symbol;

    ElectricUnit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}