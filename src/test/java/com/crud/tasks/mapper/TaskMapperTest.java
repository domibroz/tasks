package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "test_taskDto", "test_content");
        //When
        String result = taskMapper.mapToTask(taskDto1).getTitle();
        //Then
        assertEquals("test_taskDto", result);
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task1 = new Task(1L, "test_task", "test_content");
        //When
        String result = taskMapper.mapToTaskDto(task1).getTitle();
        //Then
        assertEquals("test_task", result);
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "test_task", "test_content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        //When
        String result = taskMapper.mapToTaskDtoList(taskList).get(0).getContent();
        //Then
        assertEquals("test_content", result);
    }
}
