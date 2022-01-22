package heckerpowered.ultimatetech.common.util.concurrent;

public record TickTask(Runnable runnable, CancellationToken token) {

}
