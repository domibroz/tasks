package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task1 content");
        Task task2 = new Task(2L, "Task2", "Task2 content");
        Task task3 = new Task(3L, "Task3", "Task3 content");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> resultList = dbService.getAllTasks();
        //Then
        assertEquals(3, resultList.size());
    }

    @Test
    public void getTask() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task1 content");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);

        when(repository.findById(1L)).thenReturn(java.util.Optional.ofNullable(task1));
        //When
        Optional<Task> resultTask= dbService.getTask(1L);
        //Then
        verify(repository, times(1)).findById(1L);
    }

    @Test
    public void saveTask() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task1 content");
        when(repository.save(task1)).thenReturn(task1);
        //When
        String resultContent = dbService.saveTask(task1).getContent();
        //Then
        assertEquals("Task1 content", resultContent);
    }

    @Test
    public void deleteTask() {
        //Given
        Task task1 = new Task(1L, "Task1", "Task1 content");
        //When
        dbService.deleteTask(1L);
        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}
