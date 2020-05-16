package ca.aidanmcmorranfrost.autosaver.tasks;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.util.Disk;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import org.spongepowered.api.scheduler.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScheduledSaveTask {


    public static void instantiate(Autosaver a, List<Task> collection) {
        int interval = Settings.getInstance(null).getSetting("Basic", "Save Interval").getInt();
        if (interval > 1) {
            Task t = Task.builder().async().name("Autosaver - Scheduled Save").interval(
                    interval, TimeUnit.MINUTES).execute(() -> Disk.saveWorld()).submit(a);
            collection.add(t);
        }

    }
}
