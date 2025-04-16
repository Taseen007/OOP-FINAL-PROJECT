package storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyManager implements KeyManagement {
    private final String baseDirectory;

    public KeyManager(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public List<String> getAllKeys() {
        File directory = new File(baseDirectory);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".dat"));

        if (files == null) {
            return Collections.emptyList();
        }

        List<String> keys = new ArrayList<>();
        for (File file : files) {
            String filename = file.getName();
            keys.add(filename.substring(0, filename.length() - 4)); // Remove .dat extension
        }

        return keys;
    }

    @Override
    public boolean exists(String key) {
        String filePath = baseDirectory + key + ".dat";
        return new File(filePath).exists();
    }
}