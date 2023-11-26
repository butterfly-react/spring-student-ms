package com.sha.student;

import com.sha.student.client.Address;
import com.sha.student.client.AddressClient;
import com.sha.student.mapper.JsonAdapter;
import com.sha.student.model.Student;
import com.sha.student.query.GenericQuery;
import com.sha.student.response.StudentResponse;
import com.sha.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

	@Mock
	private GenericQuery<Student> genericQuery;

	@Mock
	private AddressClient addressClient;

	@InjectMocks
	private StudentService studentService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetByIdWithAddresses() {
		Long studentId = 1L;
		Student student = new Student();
		student.setId(studentId);
		when(genericQuery.findById(Math.toIntExact(studentId), Student.class)).thenReturn(student);

		Address address = new Address();

		List<Address> addressList = Collections.singletonList(address);
		when(addressClient.getAllAddresses(studentId)).thenReturn(addressList);

		StudentResponse result = studentService.getById(Math.toIntExact(studentId));

		assertEquals(student.getName(), result.getName());
		assertEquals(student.getEmail(), result.getEmail());
		assertEquals(addressList, result.getAddressList());

		verify(genericQuery, times(1)).findById(Math.toIntExact(studentId), Student.class);
		verify(addressClient, times(1)).getAllAddresses(studentId);
	}

	@Test
	public void testGetByIdWithoutAddresses() {
		Long studentId = 1L;
		Student student = new Student();
		student.setId(studentId);
		when(genericQuery.findById(Math.toIntExact(studentId), Student.class)).thenReturn(student);

		when(addressClient.getAllAddresses(studentId)).thenReturn(null);

		StudentResponse result = studentService.getById(Math.toIntExact(studentId));

		assertEquals(student.getName(), result.getName());
		assertEquals(student.getEmail(), result.getEmail());
		assertEquals(Collections.emptyList(), result.getAddressList());

		verify(genericQuery, times(1)).findById(Math.toIntExact(studentId), Student.class);
		verify(addressClient, times(1)).getAllAddresses(studentId);
	}


	@Test
	public void testUpdateStudentNotFound() {
		int studentId = 1;
		when(genericQuery.findById(studentId, Student.class)).thenReturn(null);

		Student updatedStudent = new Student();
		updatedStudent.setName("Updated Name");
		updatedStudent.setEmail("updated@example.com");

		boolean result = studentService.update(studentId, updatedStudent);

		assertFalse(result);

		verify(genericQuery, times(1)).findById(studentId, Student.class);
		verify(genericQuery, never()).update(updatedStudent);
	}

	@Test
	public void testDeleteStudent() {
		Long studentId = 1L;
		Student existingStudent = new Student();
		existingStudent.setId(studentId);
		when(genericQuery.findById(Math.toIntExact(studentId), Student.class)).thenReturn(existingStudent);

		boolean result = studentService.delete(Math.toIntExact(studentId));

		assertTrue(result);

		verify(genericQuery, times(1)).findById(Math.toIntExact(studentId), Student.class);
		verify(genericQuery, times(1)).deleteById(Math.toIntExact(studentId), Student.class);
	}

	@Test
	public void testDeleteStudentNotFound() {
		int studentId = 1;
		when(genericQuery.findById(studentId, Student.class)).thenReturn(null);

		boolean result = studentService.delete(studentId);

		assertFalse(result);

		verify(genericQuery, times(1)).findById(studentId, Student.class);
		verify(genericQuery, never()).deleteById(studentId, Student.class);
	}

	@Test
	public void testGetAllStudents() {
		List<Student> students = Collections.singletonList(new Student());
		when(genericQuery.findAll(Student.class)).thenReturn(students);

		List<Student> result = studentService.getAll();

		assertEquals(students, result);

		verify(genericQuery, times(1)).findAll(Student.class);
	}
}
