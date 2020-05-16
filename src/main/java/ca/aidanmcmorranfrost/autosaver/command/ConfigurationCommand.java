package ca.aidanmcmorranfrost.autosaver.command;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.tasks.AutoSaverTasks;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class ConfigurationCommand {


    // todo take args for frequency
    public static CommandSpec createChangeFreqCMD(Autosaver a) {
        // todo
        return null;
    }

    public static CommandSpec createReloadCMD(Autosaver a) {
        return CommandSpec.builder().permission("AutoSaver.command.reload").executor((source, args) -> {
            source.sendMessage(Text.of("Configuration Reload in Progress!"));
            Sponge.getGame().getScheduler().createAsyncExecutor(a).execute(() -> {
                Settings.getInstance(null).reload();
                AutoSaverTasks.reload();
                source.sendMessage(Text.of("Reload Complete!"));
            });
            return CommandResult.success();
        }).build();
    }


}
