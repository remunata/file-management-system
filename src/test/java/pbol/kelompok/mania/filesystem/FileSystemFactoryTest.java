package pbol.kelompok.mania.filesystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileSystemFactoryTest {

    private final FileSystemFactory factory = new FileSystemFactory();

    @Test
    void testGetFile() {
        FileSystem file = factory.getFileSystem(FileSystemType.FILE);
        Assertions.assertEquals(file.getClass(), File.class);
    }

    @Test
    void testGetFolder() {
        FileSystem folder = factory.getFileSystem(FileSystemType.FOLDER);
        Assertions.assertEquals(folder.getClass(), Folder.class);
    }
}
