package heckerpowered.ultimatetech.common.util.energy;

import java.text.DecimalFormat;

import heckerpowered.ultimatetech.common.util.math.DoubleUtil;
import heckerpowered.ultimatetech.common.util.math.ElectricUnit;
import heckerpowered.ultimatetech.common.util.math.MeasurementUnit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class EnergyDisplay {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static final Component of(double energy, double maxEnergy, ElectricUnit unit) {
        return new TranslatableComponent("item.desc.energy").withStyle(ChatFormatting.GREEN)
                .append(getDisplay(energy, unit)).append(new TextComponent("/").withStyle(ChatFormatting.GRAY))
                .append(getDisplay(maxEnergy, unit));
    }

    public static final Component getDisplay(double value, ElectricUnit unit) {
        if (DoubleUtil.isZero(value)) {
            return getDisplay(0, ElectricUnit.JOULES, MeasurementUnit.BASE);
        }

        var values = MeasurementUnit.values();
        var length = values.length;
        for (var lowerMeasure : values) {
            var ordinal = lowerMeasure.ordinal();
            if (lowerMeasure.below(value) && ordinal == 0) {
                return getDisplay(value, unit, lowerMeasure);
            }

            int upperOridinal = ordinal + 1;
            if (upperOridinal >= length) {
                return getDisplay(value, unit, lowerMeasure);
            }
            if (upperOridinal < length) {
                var upperMeasure = values[upperOridinal];
                if ((lowerMeasure.above(value) && upperMeasure.below(value)) || lowerMeasure.getValue() == value) {
                    return getDisplay(value, unit, lowerMeasure);
                }
            }
        }

        return getDisplay(value, unit, MeasurementUnit.BASE);
    }

    public static final Component getDisplay(double value, ElectricUnit unit, MeasurementUnit measure) {
        return new TextComponent(DECIMAL_FORMAT.format(measure.process(value))).append(" ")
                .append(measure.getName(true)).append(unit.getSymbol()).withStyle(ChatFormatting.GRAY);
    }
}
