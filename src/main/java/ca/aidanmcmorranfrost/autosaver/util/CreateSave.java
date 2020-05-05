package ca.aidanmcmorranfrost.autosaver.util;

import ninja.leaping.configurate.objectmapping.Setting;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;

import java.io.*;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateSave {


    public static void saveWorld() {
        // todo throw some kind of exception if this fails or something?
        // TODO check if max saves reached and delete oldest one

        String source = Settings.getInstance(null).getSetting("Basic", "World Path").getString();
        String destination = Settings.getInstance(null).getSetting("Basic", "Backup Path").getString();

        Date saveDate = Date.from(Instant.now());
        File root = new File(destination);
        File src = new File(source);
        if (root.exists() && src.exists() && root.canWrite()) {
            String backupName = destination + saveDate.getTime() + ".zip";
            File backup = new File(backupName);
            try {
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(backup));
                checkMaxSaves();
                zipDir(src.getPath(),out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("Cannot establish connection between source and save locations, check directories and user read/write privileges");
        }


    }

    private static void checkMaxSaves() {
        // todo check if max entries exceeded and delete oldest if necessary
    }

    public static void zipDir(String loc, ZipOutputStream zOut) {
        try {
            byte[] readBuffer = new byte[1024];
            File zipDir = new File(loc);
            String[] dirs = zipDir.list();
            int bytesIn = 0;
            if (dirs==null) { // empty dir
                // todo throw and error for nothing being there
                return;
            }
            for (String s : dirs) {
                File f = new File(zipDir, s);

//                Sponge.getServer().getConsole().sendMessage(Text.of("\n Processing:  "+ f.getPath() +" \n"));
                if (f.isDirectory()) {
                    String filePath = f.getPath();
                    zipDir(filePath, zOut);
                    continue;
                }
                FileInputStream fIn = new FileInputStream(f);
                // todo delete the world dir before zipping bruh
                String zipInternalPath = f.getPath().substring(Settings.getInstance(null).getSetting("Basic","World Path").getString().length(),f.getPath().length());
                ZipEntry z = new ZipEntry(zipInternalPath);
//                ZipEntry z = new ZipEntry(f.getPath());
                zOut.putNextEntry(z);
                while ((bytesIn = fIn.read(readBuffer)) != -1) {
                    zOut.write(readBuffer, 0, bytesIn);
                }
                fIn.close();
            }
        } catch (Exception e) {
            // todo
        }

    }

}
