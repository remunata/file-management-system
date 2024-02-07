package pbol.kelompok.mania.filesystem;

public class FileSystemFactory {

    public FileSystem getFileSystem(FileSystemType type) {
        return switch (type) {
            case FOLDER -> new Folder();
            case FILE -> new File();
        };
    }
}
