package ca.aidanmcmorranfrost.autosaver.command;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.util.Settings;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;


import java.util.Stack;

public class AutoSaverCommands {
    // all commands get registered here and stuff?
    Stack<CommandSpec> commands;
    Stack<String> alias;
    Autosaver context;
    Settings conf;

    public AutoSaverCommands(Autosaver a, Settings conf) {
        commands = new Stack<>();
        alias = new Stack<>();
        context = a;
        this.conf = conf;

        // Test cmd
        commands.add(TestCommand.createTestCMD(a));
        alias.add("autosavertest");

        commands.add(ManualSaveCommand.createManualSave(a));
        alias.add("autosaver manual");


        while (!commands.empty()) {
            Sponge.getCommandManager().register(context,commands.pop(),alias.pop());
        }
    }
}
