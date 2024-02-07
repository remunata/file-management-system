package pbol.kelompok.mania.filesystem;

import pbol.kelompok.mania.exception.AlreadyExistsException;

import java.io.IOException;

public class File implements FileSystem {
    private java.io.File file;

    @Override
    public void initialize(String path) {
        file = new java.io.File(path);
    }

    @Override
    public void create() throws AlreadyExistsException, IOException {
        if (file.exists()) throw new AlreadyExistsException("File Already Exists");
        if (!file.createNewFile()) throw new IOException("Error when trying to create file");
    }

    @Override
    public void displayContent() {

    }

    @Override
    public String getPath() {
        return null;
    }
}
