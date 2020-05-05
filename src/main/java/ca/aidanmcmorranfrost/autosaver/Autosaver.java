package ca.aidanmcmorranfrost.autosaver;

import ca.aidanmcmorranfrost.autosaver.command.AutoSaverCommands;
import ca.aidanmcmorranfrost.autosaver.tasks.AutoSaverTasks;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

import javax.security.auth.login.Configuration;
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
    // enable task reoccurring functions
    // max number of saves
    // make command proper
    // reload command
    // test default config works and create more detailed info on path requirements for config folder




    //
    // Sponge.getGame().getServer().copyWorld() // use for other plugin or something
    //
    @Listener
    public void onPreInit(GamePreInitializationEvent e) {
        conf = Settings.getInstance(file);
        conf.load();
    }


    @Listener
    public void onServerStart(GameStartedServerEvent event) {
//        Settings conf = new Settings();
        AutoSaverCommands commands = new AutoSaverCommands(this, conf);
        AutoSaverTasks tasks = new AutoSaverTasks(this, conf);

    }
}
