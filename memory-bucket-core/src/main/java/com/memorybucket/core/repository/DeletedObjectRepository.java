package com.memorybucket.core.repository;

import com.memorybucket.core.domain.DeletedObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedObjectRepository extends JpaRepository<DeletedObject, Long> {
}
