package pbol.kelompok.mania.handler;

import pbol.kelompok.mania.exception.AlreadyExistsException;
import pbol.kelompok.mania.filesystem.File;
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
    private static CommandHandler instance;

    private CommandHandler() {
        factory = new FileSystemFactory();
        currentDir = factory.getFileSystem(FileSystemType.FOLDER);
        currentDir.initialize(System.getProperty("user.dir"));
    }

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    public void help() {
        System.out.println("Command available:");
        System.out.println("ls\t - showing all files and directories in current directory");
        System.out.println("cwd\t - showing current working directory path");
        System.out.println("cd [dirpath]\t - change working directory");
        System.out.println("mkdir [dirname]\t - create mew directory");
        System.out.println("touch [filename]\t - create new file");
        System.out.println("cat [filename]\t - show file content(s)");
        System.out.println("append [filename] [content]\t - append content to the end of file");
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

    public void cat(String arg) {
        String fileName = arg.trim();

        FileSystem file = factory.getFileSystem(FileSystemType.FILE);
        file.initialize(currentDir.getPath() + "/" + fileName);
        file.displayContent();
    }

    public void append(String arg) {
        String[] args = arg.trim().split(" ", 2);
        if (args.length < 2) {
            System.err.println("Invalid arguments");
            return;
        }

        FileSystem file = factory.getFileSystem(FileSystemType.FILE);
        file.initialize(currentDir.getPath() + "/" + args[0]);
        file.update(args[1]);
    }

    public void rm(String arg) {
        String fileName = arg.trim();
        System.out.println("Removing file " + fileName);
    }

    public void mv(String arg) {
        System.out.println("Moving directory" + arg.trim());
    }
}
