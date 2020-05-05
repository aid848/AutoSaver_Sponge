package ca.aidanmcmorranfrost.autosaver.util;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import java.io.File;
import java.io.IOException;

public class Settings {

    static Settings set;

    File configLocation;
    File config;
    File worldSaves;
    ConfigurationLoader<CommentedConfigurationNode> loader;
    CommentedConfigurationNode confNode;

    public static Settings getInstance(File file) {
        if(set == null){
            set = new Settings(file);
        }
        return set;
    }

    private Settings(File file) {
        configLocation = file;

    }



    private void setup() {
        // todo initial setup values but refactor to ENUM or constants pls

        confNode.getNode("Basic", "save interval").setValue(-1).setComment("save interval in minutes (-1 to disable)");
        confNode.getNode("Basic", "Backup Path").setValue(configLocation.getAbsolutePath() + "\\backups\\").setComment("root folder for saved worlds");

        //todo the world path is wrong it goes to the config path + world :(
        // C:\\Users\\Aidan\\Desktop\\sponge test\\.\\config\\autosaver\\config.json\\world
        String defaultWorldPath = config.getAbsolutePath().substring(0,config.getAbsolutePath().length()-29) + "\\world";
        confNode.getNode("Basic", "World Path").setValue(defaultWorldPath).setComment("folder path for live world");

    }

    public <T> void setSetting(String nodeName, T value) {
        // todo
        save();
    }

    public CommentedConfigurationNode getSetting(@NonNull Object... path) {
        return confNode.getNode(path);
    }

    public void load() {
        try {
        if(!configLocation.exists()) {
                configLocation.mkdir();
        }
        config = new File(configLocation, "config.json");
        if(!config.exists()) {
            config.createNewFile();
            loader = HoconConfigurationLoader.builder().setFile(config).build();
            confNode = loader.load();
            setup();
            loader.save(confNode);
        } else {
            loader = HoconConfigurationLoader.builder().setFile(config).build();
            confNode = loader.load();
        }
        confNode = loader.load();
        worldSaves = new File(confNode.getNode("Basic", "Backup Path").getString());
        if(!worldSaves.exists()) {
            worldSaves.mkdir();
        }
        } catch (IOException e) {
            Sponge.getServer().getConsole().sendMessage(Text.of(e.toString()));
        }
    }

    public void save() {
        try {
            loader.save(confNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        //todo
    }


}
