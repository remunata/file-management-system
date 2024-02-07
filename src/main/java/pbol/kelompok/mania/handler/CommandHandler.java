package pbol.kelompok.mania.handler;

import pbol.kelompok.mania.exception.AlreadyExistsException;
import pbol.kelompok.mania.filesystem.FileSystem;
import pbol.kelompok.mania.filesystem.FileSystemFactory;
import pbol.kelompok.mania.filesystem.FileSystemType;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CommandHandler {
    private final FileSystemFactory factory;
    private final FileSystem currentDir;

    public CommandHandler() {
        factory = new FileSystemFactory();
        currentDir = factory.getFileSystem(FileSystemType.FOLDER);
        currentDir.initialize(System.getProperty("user.dir"));
    }

    public void help() {
        System.out.println("Command available:");
        System.out.println("ls\t- showing all files and directories in current directory");
        System.out.println("cwd\t- showing current working directory path");
        System.out.println("cd [arguments]\t- change working directory");
        System.out.println("mkdir [argument]\t- create mew directory");
        System.out.println("touch [argument]\t- create new file");
    }

    public void cwd() {
        System.out.println(currentDir.getPath());
    }

    public void ls() {
        currentDir.displayContent();
    }

    public void cd(String args) {
        List<String> dirs = new LinkedList<>(
                Arrays.stream(currentDir.getPath().split("/")).toList()
        );
        String[] newDirs = args.split("/");

        for (String dir : newDirs) {
            if (dir.equals("..")) dirs.remove(dirs.size() - 1);
            else dirs.add(dir);
        }

        String newPath = dirs
                .stream()
                .reduce("", (partialString, element) -> partialString + "/" + element);

        currentDir.initialize(newPath);
    }

    public void mkdir(String arg) {
        String dirName = arg.trim();

        if (dirName.contains(" ")) {
            System.err.println("Invalid folder name: " + arg);
            return;
        }

        String dirPath = currentDir.getPath() + "/" + dirName;

        try {
            FileSystem newDir = factory.getFileSystem(FileSystemType.FOLDER);
            newDir.initialize(dirPath);
            newDir.create();
        } catch (AlreadyExistsException exception) {
            System.err.println("Directory " + arg + " already exists");
        } catch (IOException exception) {
            System.err.println("Error occurred when trying to create directory");
        }
    }

    public void touch(String arg) {
        String fileName = arg.trim();

        if (fileName.contains(" ")) {
            System.err.println("Invalid folder name: " + arg);
            return;
        }

        String filePath = currentDir.getPath() + "/" + fileName;

        try {
            FileSystem newFile = factory.getFileSystem(FileSystemType.FILE);
            newFile.initialize(filePath);
            newFile.create();
        } catch (AlreadyExistsException exception) {
            System.err.println("File " + arg + " already exists");
        } catch (IOException exception) {
            System.err.println("Error occurred when trying to create file");
        }
    }
}
