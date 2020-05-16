package ca.aidanmcmorranfrost.autosaver.tasks;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import org.spongepowered.api.scheduler.Task;

import java.util.ArrayList;
import java.util.List;


public class AutoSaverTasks {

    static AutoSaverTasks self;
    Settings conf;
    Autosaver context;
    List<Task> tasks;


    public AutoSaverTasks(Autosaver a, Settings conf) { // todo change to singleton
        this.conf = conf;
        context = a;
        self = this;
        init();
    }

    private static void init() {
        // create tasks here
        self.tasks = new ArrayList<>();
        ScheduledSaveTask.instantiate(self.context, self.tasks);
    }

    public static void reload() {
        for (Task t: self.tasks) {
            t.cancel();
        }
        init();
    }

    // TODO
    public void editTask() {
        // cancel and then add task with methods
//        throw new NotImplementedException();
    }

    public void addTask() {
//        throw new NotImplementedException();
    }

    public void cancelTask() {
//        throw new NotImplementedException();
    }

}
