package heckerpowered.ultimatetech.common.util.concurrent.cancellation;

import heckerpowered.ultimatetech.common.util.concurrent.CancellationToken;

public abstract class ConditionCancellation {
    public abstract boolean shouldCancel(CancellationToken token);

    public static final ConditionCancellation afterTicks(int tickCount) {
        return new TickCancellation(tickCount);
    }

    public static final ConditionCancellation instant() {
        return new TickCancellation(0);
    }
}
