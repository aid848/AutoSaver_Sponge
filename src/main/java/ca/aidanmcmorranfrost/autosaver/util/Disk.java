package ca.aidanmcmorranfrost.autosaver.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Disk {


    public static void saveWorld() {
        String source = Settings.getInstance(null).getSetting("Basic", "World Path").getString();
        String destination = Settings.getInstance(null).getSetting("Basic", "Backup Path").getString();
        if (source == null || destination == null)
            throw new RuntimeException("no valid path input(s)");
        Date saveDate = Date.from(Instant.now());
        File root = new File(destination);
        File src = new File(source);
        if (root.exists() && src.exists() && root.canWrite()) {
            String backupName = destination + saveDate.getTime() + ".zip";
            File backup = new File(backupName);
            try {
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(backup));
                if (!checkMaxSaves(destination)) {
                    throw new RuntimeException("unable to clear space for new world save");
                }
                zipDir(src.getPath(),out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("Cannot establish connection between source and save locations, check directories and user read/write privileges");
        }


    }

    // REQUIRES: directory exists and is valid
    // MODIFIES: world saves
    // EFFECTS: check if max entries exceeded and delete oldest if necessary
    private static boolean checkMaxSaves(String dest) {
        File worldFolder = new File(dest);
        int maxLength = Settings.getInstance(null).getSetting("Basic", "Maximum Saves on Disk").getInt();
        if (maxLength < 1) {
            maxLength = 5; // defaults to 5 if invalid
        }
        File[] savedWorlds = filterInvalidSaves(worldFolder.listFiles()); // ignore non world saves in directory
        if(savedWorlds.length > maxLength) {
            Arrays.sort(savedWorlds);
            File oldest = savedWorlds[0];
            return oldest.delete();
        } else {
            return true;
        }

    }

    private static File[] filterInvalidSaves(File[] in) {
        if (in == null)
            return new File[]{};
        List<File> allowed = new ArrayList<>();
        for (File f: in) {
            if (f.getName().endsWith(".zip")) {
                allowed.add(f);
            }
        }
        return allowed.toArray(new File[]{});
    }

    public static void zipDir(String loc, ZipOutputStream zOut) {
        try {
            byte[] readBuffer = new byte[1024];
            File zipDir = new File(loc);
            String[] dirs = zipDir.list();
            int bytesIn;
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
                String zipInternalPath = f.getPath().substring(
                        Settings.getInstance(null).getSetting(
                                "Basic","World Path").getString().length());
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
