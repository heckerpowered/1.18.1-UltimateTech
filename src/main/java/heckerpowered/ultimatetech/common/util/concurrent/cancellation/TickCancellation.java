package heckerpowered.ultimatetech.common.util.concurrent.cancellation;

import heckerpowered.ultimatetech.common.util.concurrent.CancellationToken;

public final class TickCancellation extends ConditionCancellation {
    private final int tickCount;

    public TickCancellation(int tickCount) {
        this.tickCount = tickCount;
    }

    @Override
    public boolean shouldCancel(CancellationToken token) {
        return token.getTickCount() > tickCount;
    }

}
