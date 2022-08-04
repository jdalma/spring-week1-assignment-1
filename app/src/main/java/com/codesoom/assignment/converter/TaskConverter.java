package com.codesoom.assignment.converter;

import com.codesoom.assignment.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

public class TaskConverter {

    public static TaskConverter getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder{
        public static final TaskConverter INSTANCE = new TaskConverter();
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    public Task convert(String content) throws JsonProcessingException {
        return objectMapper.readValue(content , Task.class);
    }

    public String convert(Task task) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream , task);
        return outputStream.toString();
    }

    public String convert(Map<Long , Task> tasks) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream , new ArrayList<>(tasks.values()));
        return outputStream.toString();
    }
}