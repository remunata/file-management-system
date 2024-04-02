package pbol.kelompok.mania.filesystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pbol.kelompok.mania.exception.AlreadyExistsException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileTest {

    private final File file = new File();

    @BeforeEach
    void setUp() {
        java.io.File index = new java.io.File("test");
        if (!index.exists()) {
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
    void testCreateFile() throws AlreadyExistsException, IOException {
        file.initialize("test/test.txt");
        file.create();
        Assertions.assertTrue(new java.io.File("test/test.txt").exists());
    }

    @Test
    void testDuplicateFile() throws AlreadyExistsException, IOException {
        file.initialize("test/duplicate.txt");
        file.create();
        Assertions.assertThrowsExactly(AlreadyExistsException.class, file::create);
    }

    @Test
    void testWriteAndRead() throws AlreadyExistsException, IOException {
        file.initialize("test/content.txt");
        file.create();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        file.displayContent();
        Assertions.assertEquals("", outputStream.toString());

        String content = "This is from testing";
        file.update(content);

        file.displayContent();
        Assertions.assertEquals(content, outputStream.toString().trim());
    }
}
