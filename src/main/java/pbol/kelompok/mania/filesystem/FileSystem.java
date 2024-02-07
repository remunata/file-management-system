package pbol.kelompok.mania.filesystem;

import pbol.kelompok.mania.exception.AlreadyExistsException;

import java.io.IOException;

public interface FileSystem {
    void initialize(String path);
    void create() throws AlreadyExistsException, IOException;
    void displayContent();
    void update(String options);
    String getPath();
}
