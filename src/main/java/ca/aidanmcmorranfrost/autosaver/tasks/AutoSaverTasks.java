package ca.aidanmcmorranfrost.autosaver.tasks;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import org.spongepowered.api.scheduler.Task;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.List;
import java.util.Stack;

public class AutoSaverTasks {

    List<Task.Builder> runningTasks;
    Settings conf;
    Autosaver context;
    List<Task.Builder> tasks;

    public AutoSaverTasks(Autosaver a, Settings conf) {
        this.conf = conf;
        context = a;
        tasks = new Stack<Task.Builder>();

//        TestTask test = new TestTask(conf.getSetting(new String[]{"Basic", "save interval"}));
        // todo create tasks here



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
