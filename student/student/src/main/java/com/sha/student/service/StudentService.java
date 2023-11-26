package com.sha.student.service;


import com.sha.student.client.Address;
import com.sha.student.client.AddressClient;
import com.sha.student.mapper.JsonAdapter;
import com.sha.student.model.Student;
import com.sha.student.query.GenericQuery;
import com.sha.student.response.StudentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final GenericQuery<Student> genericQuery;
    private final AddressClient addressClient;

    public List<Student> getAll() {
        return genericQuery.findAll(Student.class);
    }


    public StudentResponse getById(int id) {
        Student student = genericQuery.findById(id, Student.class);
        List<Address> addressList = addressClient.getAllAddresses(student.getId());

        if (addressList != null) {
            return JsonAdapter.convertToStudentResponse(student, addressList);
        } else {
            return JsonAdapter.convertToStudentResponse(student, Collections.emptyList());
        }
    }
    public void create(Student student) {
        genericQuery.save(student);
    }

    public boolean update(int id, Student student) {
        Student existingEntity = genericQuery.findById(id, Student.class);
        if (existingEntity != null) {
            genericQuery.update(student);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean delete(int id) {
        genericQuery.deleteById(id, Student.class);
        return Boolean.TRUE;
    }
}

