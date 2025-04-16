// src/storage/KeyManagement.java
package storage;

import java.util.List;

public interface KeyManagement {
    List<String> getAllKeys();
    boolean exists(String key);
}