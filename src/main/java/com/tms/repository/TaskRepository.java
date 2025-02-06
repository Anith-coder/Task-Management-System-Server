package com.tms.repository;

import com.tms.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByTitleContainingIgnoreCase(String title);
    List<Task> findByStatus(String status);
}

