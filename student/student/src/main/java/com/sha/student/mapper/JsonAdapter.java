package com.sha.student.mapper;

import com.google.gson.*;
import com.sha.student.client.Address;
import com.sha.student.model.Student;
import com.sha.student.response.StudentResponse;

import java.util.List;


public class JsonAdapter {

    public static JsonSerializer<Student> getSerializer() {
        return (student, typeOfSrc, context) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", student.getName());
            jsonObject.addProperty("email", student.getEmail());
            jsonObject.addProperty("address", "");
            return jsonObject;
        };
    }


    public static JsonDeserializer<Student> getDeserializer() {
        return (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            String email = jsonObject.get("email").getAsString();
            return Student.builder()
                    .name(name)
                    .email(email)
                    .build();
        };
    }

    public static StudentResponse convertToStudentResponse(Student student, List<Address> adressList) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Student.class, getSerializer())
                .create();
        String json = gson.toJson(student);
        StudentResponse studentResponse = gson.fromJson(json, StudentResponse.class);
        studentResponse.setAddressList(adressList);
        return studentResponse;
    }
}
