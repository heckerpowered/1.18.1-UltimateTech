package heckerpowered.ultimatetech.common.event;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import heckerpowered.ultimatetech.common.util.concurrent.TickTask;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class CommonTickEventHandler {
    // High sequential access performance
    private static final List<TickTask> TASKS = Collections.synchronizedList(Lists.newLinkedList());

    private CommonTickEventHandler() {
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public static void onServerTick(final ServerTickEvent event) {
        tickTask();
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(final ClientTickEvent event) {
        tickTask();
    }

    private static void tickTask() {
        var iterator = TASKS.iterator();
        while (iterator.hasNext()) {
            var task = iterator.next();
            var token = task.token();
            if (token.isCancellationRequested() || token.shouldCancel()) {
                token.cancel();
                iterator.remove();
                continue;
            }

            task.runnable().run();
            token.tick();
        }
    }

    public static void addTask(TickTask task) {
        TASKS.add(task);
    }
}
