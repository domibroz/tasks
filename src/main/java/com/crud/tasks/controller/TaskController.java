package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") String taskId) {
        return new TaskDto((long) 1, "test title", "test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{taskId}")
    public boolean deleteTask(@PathVariable("taskId") String taskId) {
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long) 1, "Edited test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTask(TaskDto taskDto) {
        return "New task created";
    }
}
