import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress save1 = new GameProgress(70, 5, 4, 20);
        GameProgress save2 = new GameProgress(45, 6, 5, 50);
        GameProgress save3 = new GameProgress(30, 5, 7, 70);

        List<String> saveGames = Arrays.asList(
                "E:/Games/savegames/save1.dat",
                "E:/Games/savegames/save2.dat",
                "E:/Games/savegames/save3.dat");

        saveGame("E:/Games/savegames/save1.dat", save1);
        saveGame("E:/Games/savegames/save2.dat", save2);
        saveGame("E:/Games/savegames/save3.dat", save3);

        zipFiles("E:/Games/savegames/save.zip", saveGames);

        File game1Dat = new File("E:/Games/savegames/save1.dat");
        File game2Dat = new File("E:/Games/savegames/save2.dat");
        File game3Dat = new File("E:/Games/savegames/save3.dat");

        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }


    public static boolean saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean zipFiles(String path, List<String> lists) {
        File zipFile = new File(path);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String list : lists) {
                File file = new File(list);

                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


}
