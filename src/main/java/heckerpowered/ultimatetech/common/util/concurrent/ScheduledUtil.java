package heckerpowered.ultimatetech.common.util.concurrent;

import heckerpowered.ultimatetech.common.event.CommonTickEventHandler;

public final class ScheduledUtil {
    private ScheduledUtil() {
    }

    public static final CancellationToken addTask(final Runnable runnable) {
        var token = new CancellationToken();
        CommonTickEventHandler.addTask(new TickTask(runnable, token));
        return token;
    }
}
