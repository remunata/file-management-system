package pbol.kelompok.mania.handler;

public interface CommandHandler {

    void help();
    void cwd();
    void ls();
    void cd(String args);
    void mkdir(String args);
    void touch (String args);
    void cat(String args);
    void append(String args);
    void rm(String args);
    void mv(String args);
}
