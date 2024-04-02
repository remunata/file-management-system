package pbol.kelompok.mania.filesystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pbol.kelompok.mania.exception.AlreadyExistsException;

import java.io.IOException;

public class FolderTest {

    private final Folder folder = new Folder();

    @BeforeEach
    void setUp() {
        java.io.File index = new java.io.File("test");
        if (!index.exists()) {
            index.mkdir();
        } else {
            index.delete();
            index.mkdir();
        }
    }

    @AfterEach
    void tearDown() {
        java.io.File index = new java.io.File("test");
        String[]entries = index.list();
        for(String s: entries){
            java.io.File currentFile = new java.io.File(index.getPath(),s);
            currentFile.delete();
        }
        index.delete();
    }

    @Test
    void testCreateFolder() throws AlreadyExistsException, IOException {
        folder.initialize("test/test");
        folder.create();
        Assertions.assertTrue(new java.io.File("test/test").exists());
    }

    @Test
    void testDuplicate() throws AlreadyExistsException, IOException {
        folder.initialize("test/test");
        folder.create();
        Assertions.assertThrowsExactly(AlreadyExistsException.class, folder::create);
    }

    @Test
    void testGetPath() {
        String path = "test/test";
        folder.initialize(path);
        Assertions.assertEquals(new java.io.File(path).getPath(), folder.getPath());
    }
}
