package ca.aidanmcmorranfrost.autosaver.command;

import ca.aidanmcmorranfrost.autosaver.*;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class TestCommand {

    public static CommandSpec createTestCMD(Autosaver a) {
        return CommandSpec.builder().permission("AutoSaver.command.test").executor((source, args) -> {
            source.sendMessage(Text.of("ping!"));
            return CommandResult.success();
        }).build();
    }
}
