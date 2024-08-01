package com.memorybucket.storage.service;

public interface ObjectStorageService {

    String getUploadUrl(String key);

    void deleteObject(String key);
}
