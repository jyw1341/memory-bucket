package com.memorybucket.batch;

import com.memorybucket.core.domain.DeletedObject;
import com.memorybucket.storage.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class DeletedObjectProcessor implements ItemProcessor<DeletedObject, DeletedObject> {

    private final ObjectStorageService objectStorageService;

    @Override
    public DeletedObject process(DeletedObject item) {
        objectStorageService.deleteObject(item.getUrl());
        return item;
    }
}
