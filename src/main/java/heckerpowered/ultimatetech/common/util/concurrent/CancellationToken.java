package heckerpowered.ultimatetech.common.util.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import heckerpowered.ultimatetech.common.util.concurrent.cancellation.ConditionCancellation;

public class CancellationToken {
    private boolean cancellationRequested;
    private int tickCount;
    private final LinkedList<ConditionCancellation> conditions = Lists.newLinkedList();
    private List<Runnable> runnable = Lists.newArrayList();
    private List<Runnable> with = Lists.newArrayList();

    public final void cancel() {
        if (!cancellationRequested) {
            cancellationRequested = true;
            runnable.forEach(Runnable::run);
        }
    }

    public final boolean isCancellationRequested() {
        return cancellationRequested;
    }

    public final void cancel(boolean condition) {
        if (condition)
            cancel();
    }

    public final void cancel(Supplier<Boolean> condition) {
        cancel(condition.get());
    }

    public final CancellationToken cancel(ConditionCancellation condition) {
        conditions.add(condition);
        return this;
    }

    public final CancellationToken with(Runnable runnable) {
        this.with.add(runnable);
        return this;
    }

    public final CancellationToken then(Runnable runnable) {
        this.runnable.add(runnable);
        return this;
    }

    public final boolean shouldCancel() {
        if (conditions.isEmpty()) {
            return false;
        }

        var iterator = conditions.iterator();
        while (iterator.hasNext()) {
            var condition = iterator.next();
            if (condition.shouldCancel(this)) {
                return true;
            }
        }

        return false;
    }

    public int getTickCount() {
        return tickCount;
    }

    public void tick() {
        if (tickCount++ == 0) {
            start();
        }
    }

    public void start() {
        with.forEach(Runnable::run);
    }
}
