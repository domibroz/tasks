package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TaskRepository extends CrudRepository<Task, Long> {
    @NotNull
    @Override
    List<Task> findAll();

    @NotNull
    @Override
    Optional<Task> findById(@NotNull Long taskId);

    @NotNull
    @Override
    Task save(@NotNull Task task);

    @Override
    void deleteById(@NotNull Long taskId);

    @Override
    long count();
}
