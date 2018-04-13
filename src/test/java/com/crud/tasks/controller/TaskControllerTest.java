package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTasksList() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskDtoList = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTasksList() throws Exception {
        //Given
        List<Task> taskLists = new ArrayList<>();
        taskLists.add(new Task(1L, "Task_title", "Task_content"));

        List<TaskDto> taskListsDto = new ArrayList<>();
        taskListsDto.add(new TaskDto(1l, "Task_title", "Task_content"));
        when(taskMapper.mapToTaskDtoList(taskLists)).thenReturn(taskListsDto);
        when(dbService.getAllTasks()).thenReturn(taskLists);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Task_title")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "TestTask", "TaskContent");
        TaskDto task1Dto = new TaskDto(1L, "TestTask", "TaskContent");
        when(taskMapper.mapToTaskDto(task1)).thenReturn(task1Dto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task1));

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("TestTask")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "TestTask", "TaskContent");
        TaskDto task1Dto = new TaskDto(1L, "TestTask", "TaskContent");
        when(taskMapper.mapToTaskDto(task1)).thenReturn(task1Dto);
        when(dbService.saveTask(task1)).thenReturn(task1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task1Dto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateNewTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "TestTask", "TaskContent");
        TaskDto task1Dto = new TaskDto(1L, "TestTask", "TaskContent");
        when(taskMapper.mapToTask(task1Dto)).thenReturn(task1);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task1);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}