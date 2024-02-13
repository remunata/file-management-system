package pbol.kelompok.mania.filesystem;

import pbol.kelompok.mania.exception.AlreadyExistsException;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        try (Scanner scanner = new Scanner(file);) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException exception) {
            System.err.println("File not found");
        }
    }

    @Override
    public void update(String content) {
        StringBuilder currentContent = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine())
                currentContent.append(scanner.nextLine());
        } catch (FileNotFoundException exception) {
            System.err.println("File not found");
        }

        try (FileWriter writer = new FileWriter(file.getPath())) {
            writer.write(currentContent.toString() + "\n");
            writer.write(content);
        } catch (IOException ioException) {
            System.err.println("Error when writing to file");
        }
    }

    @Override
    public void delete(String name) {
        System.out.println("Deleting file " + name);
    }

    @Override
    public String getPath() {
        return null;
    }
}
