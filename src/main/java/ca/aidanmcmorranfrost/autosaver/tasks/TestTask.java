package ca.aidanmcmorranfrost.autosaver.tasks;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

public class TestTask {
    Task.Builder task;

    public TestTask(String text) {
        task = Task.builder().execute(new Runnable() {
            @Override
            public void run() {
                Sponge.getServer().getConsole().sendMessage(Text.of(text));
            }
        });

    }

}
