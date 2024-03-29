package pbol.kelompok.mania;

import pbol.kelompok.mania.handler.CommandHandler;
import pbol.kelompok.mania.handler.CommandHandlerImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = CommandHandlerImpl.getInstance();

        System.out.println("Starting File Management System");
        System.out.println("Type \"help\" to show all commands");

        mainLoop:
        while (true) {
            System.out.println("\n");
            commandHandler.cwd();
            System.out.print("$ ");

            String command = scanner.nextLine();
            String[] commandArr = command.split(" ", 2);

            switch (commandArr[0]) {
                case "help" -> commandHandler.help();
                case "exit" -> {
                    System.out.println("Thank you for using our program");
                    scanner.close();
                    break mainLoop;
                }
                case "ls" -> commandHandler.ls();
                case "cwd" -> commandHandler.cwd();
                case "cd" -> commandHandler.cd(commandArr[1]);
                case "mkdir" -> commandHandler.mkdir(commandArr[1]);
                case "touch" -> commandHandler.touch(commandArr[1]);
                case "cat" -> commandHandler.cat(commandArr[1]);
                case "append" -> commandHandler.append(commandArr[1]);
                case "rm" -> commandHandler.rm(commandArr[1]);
                case "mv" -> commandHandler.mv(commandArr[1]);
                default -> System.out.println("Invalid command: " + commandArr[0]);
            }
        }
    }
}
