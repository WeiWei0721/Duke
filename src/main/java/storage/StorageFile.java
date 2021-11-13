package storage;


import exception.BusinessException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


import task.*;

/**
 * Represents the file used to store task list data.
 */
public class StorageFile {

    //public static final String DEFAULT_STORAGE_FILEPATH = "data/Duke.txt";
    public final String home = System.getProperty("user.home");
    public String filePath;
    public String absoluteFilePath;
    public Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
//    public StorageFile() throws InvalidStorageFilePathException {
//        this(DEFAULT_STORAGE_FILEPATH);
//    }


//    public StorageFile(String filePath) throws InvalidStorageFilePathException {
//        this.path = Paths.get(filePath);
//        if (!isValidPath(path)) {
//            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
//        }
//    }

    public StorageFile(String filePath){
        this.filePath = filePath;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public void Init() throws StorageOperationException{
        try{
            path = Paths.get(home,"duke",filePath);
            absoluteFilePath = Paths.get(home,"duke",filePath).toString();
            if(Files.exists(path)){// reading data from exist file.
                System.out.println("Reading from exist file.");
                File f = new File(absoluteFilePath);
                Scanner s = new Scanner(f);
                while(s.hasNext()){
                    System.out.println(s.nextLine());
                }
            }else{//creating new file.
                System.out.println("Creating file.");
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

        }catch (IOException e){
            throw new StorageOperationException(e.getMessage());
        }
    }

    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */

    public TaskList load() throws StorageOperationException {
        Init();

        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        }catch (FileNotFoundException fnfe){
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        //other errors
        }catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }catch (BusinessException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    /**
     * Saves the {@code task} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(Task newAddTask) throws StorageOperationException {
        try{
            TaskListEncoder.encodeTask(newAddTask, path.toString());
        }catch (IOException ioe){
            throw new StorageOperationException("Error writing to file： " + path);
        }
    }

    /**
     * Saves the {@code taskList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveTaskList(List<Task> taskList) throws StorageOperationException {
        try{
            TaskListEncoder.encodeTaskList(taskList, path.toString());
        }catch (IOException ioe){
            throw new StorageOperationException("Error writing to file： " + path);
        }
    }


    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public String getPath() {
        return path.toString();
    }


    public static class InvalidStorageFilePathException extends BusinessException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}
