package ca.aidanmcmorranfrost.autosaver.command;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;

import java.util.Stack;

public class AutoSaverCommands {
    // all commands get registered here and stuff?
    Stack<CommandSpec> commands;
    Stack<String[]> callCommand;
    Autosaver context;
    Settings conf;

    public AutoSaverCommands(Autosaver a, Settings conf) {
        commands = new Stack<>();
        callCommand = new Stack<>();
        context = a;
        this.conf = conf;

        // Test cmd
        commands.add(TestCommand.createTestCMD(a));
        callCommand.add(new String[]{"autosaver_test"});

        commands.add(ManualSaveCommand.createManualSave(a));
        callCommand.add(new String[]{"autosaver_manual", "as_m"});

        commands.add(ConfigurationCommand.createReloadCMD(a));
        callCommand.add(new String[]{"autosaver_reload", "as_rl"});


        while (!commands.empty()) {
            Sponge.getCommandManager().register(context,commands.pop(), callCommand.pop());
        }
    }
}
