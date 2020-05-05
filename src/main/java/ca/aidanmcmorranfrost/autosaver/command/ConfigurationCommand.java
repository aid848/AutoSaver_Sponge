package ca.aidanmcmorranfrost.autosaver.command;

import ca.aidanmcmorranfrost.autosaver.Autosaver;
import ca.aidanmcmorranfrost.autosaver.util.CreateSave;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class ConfigurationCommand {


    // todo take args for frequency
//    public static CommandSpec createChangeFreqCMD(Autosaver a) {
//        CommandSpec cmd = CommandSpec.builder().permission("AutoSaver.command.frequency").executor((source,args) -> {
//            source.sendMessage(Text.of("trying to copy world"));
//            Sponge.getGame().getScheduler().createAsyncExecutor(a).execute(new Runnable() {
//                @Override
//                public void run() {
//                    CreateSave.saveWorld();
//                    source.sendMessage(Text.of("Save complete"));
//                }
//            });
//            return CommandResult.success();
//        }).build();
//        return cmd;
//    }


}
