package com.memorybucket.batch;

import com.memorybucket.core.domain.DeletedObject;
import com.memorybucket.core.repository.DeletedObjectRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final DeletedObjectRepository repository;

    @PostConstruct
    public void init() {
        for (long i = 1; i <= 1; i++) {
            DeletedObject object = new DeletedObject(i, "horo.png");
            repository.save(object);
        }
    }
}
