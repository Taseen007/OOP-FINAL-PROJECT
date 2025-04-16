package storage;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class FileDataStore implements DataStore {
    private final String baseDirectory;
    private final KeyManagement keyManagement;

    public FileDataStore() {
        this.baseDirectory = "data/";
        this.keyManagement = new KeyManager(baseDirectory);
        File directory = new File(baseDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public <T> void save(String key, T data) {
        String filePath = baseDirectory + key + ".dat";
        try (var oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save data: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> load(String key) {
        String filePath = baseDirectory + key + ".dat";
        File file = new File(filePath);

        if (!file.exists()) {
            return Optional.empty();
        }

        try (var ois = new ObjectInputStream(new FileInputStream(filePath))) {
            T data = (T) ois.readObject();
            return Optional.of(data);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load data: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(String key) {
        String filePath = baseDirectory + key + ".dat";
        File file = new File(filePath);
        return file.exists() && file.delete();
    }

    public boolean exists(String key) {
        return keyManagement.exists(key);
    }

    public List<String> getAllKeys() {
        return keyManagement.getAllKeys();
    }
}