package com.sha.student.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sha.student.model.Student;


public class JsonMapper {
    private static Gson initializeGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, JsonAdapter.getSerializer());
        builder.registerTypeAdapter(Student.class, JsonAdapter.getDeserializer());
        return builder.create();
    }

    public static String serializeStudent(Student student) {
        Gson gson = initializeGson();
        return gson.toJson(student);
    }


    public static Student deserializeStudent(String json) {
        Gson gson = initializeGson();
        return gson.fromJson(json, Student.class);
    }
}
