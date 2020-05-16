package ca.aidanmcmorranfrost.autosaver.command;

import ca.aidanmcmorranfrost.autosaver.*;
import ca.aidanmcmorranfrost.autosaver.util.Disk;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class ManualSaveCommand {

    public static CommandSpec createManualSave(Autosaver a) {
        CommandSpec.Builder builder = CommandSpec.builder();
        builder.permission("AutoSaver.command.manual");
        builder.executor((source, args) -> {
            source.sendMessage(Text.of("trying to copy world"));
            Sponge.getGame().getScheduler().createAsyncExecutor(a).execute(() -> {
                Disk.saveWorld();
                source.sendMessage(Text.of("Save complete"));
            });
            return CommandResult.success();
        });
        return builder.build();
    }

}
