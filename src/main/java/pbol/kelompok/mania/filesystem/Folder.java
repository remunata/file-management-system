package pbol.kelompok.mania.filesystem;

import pbol.kelompok.mania.exception.AlreadyExistsException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Folder implements FileSystem {
    private File folder;

    @Override
    public void initialize(String path) {
        folder = new File(path);
    }

    @Override
    public void create() throws AlreadyExistsException, IOException {
        if (folder.exists()) throw new AlreadyExistsException("Directory already exists");
        if (!folder.mkdirs()) throw new IOException("Error occured when trying to create directory");
    }

    @Override
    public void displayContent() {
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            Arrays.sort(listOfFiles);
            for (File file : listOfFiles) {
                if (file.isFile())
                    System.out.println("[FILE]\t" + file.getName());
                else
                    System.out.println("[DIR]\t" + file.getName());
            }
        }
    }

    @Override
    public String getPath() {
        return folder.getPath();
    }
}
