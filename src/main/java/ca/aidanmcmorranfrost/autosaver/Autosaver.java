package ca.aidanmcmorranfrost.autosaver;

import ca.aidanmcmorranfrost.autosaver.command.AutoSaverCommands;
import ca.aidanmcmorranfrost.autosaver.tasks.AutoSaverTasks;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;


@Plugin(
        id = "autosaver",
        name = "autoSaver",
        description = "Provides regular auto saving for sponge servers",
        url = "http://aidanmcmorranfrost.ca/",
        authors = {
                "Aidan Frost"
        }
)
public class Autosaver {

    AutoSaverCommands cmds;
    AutoSaverTasks tsks;

    final Boolean DEBUG = true;
    static Settings conf;

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File file;

    // TODO
    // clean up zip structure to not include outer structure
    // make filename better


    // * to test
    // test task reoccurring functions
    // test reload command
    // test default config works and create more detailed info on path requirements for config folder
    // test max number of saves

    @Listener
    public void onPreInit(GamePreInitializationEvent e) {
        conf = Settings.getInstance(file);
        conf.load();
    }


    @Listener
    public void onServerStart(GameStartedServerEvent e) {
        cmds = new AutoSaverCommands(this, conf);
        tsks = new AutoSaverTasks(this, conf);
    }

    @Listener
    public void onReload(GameReloadEvent e) {
        // todo refactor to reload method since command should have similar behavior
        Settings.getInstance(null).reload();
        AutoSaverTasks.reload();
    }

}
