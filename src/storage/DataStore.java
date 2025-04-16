// src/storage/DataStore.java
package storage;

import java.util.Optional;

public interface DataStore {
    <T> void save(String key, T data);
    <T> Optional<T> load(String key);
    boolean delete(String key);
}