package com.sha.student.response;

import com.sha.student.client.Address;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse {

    private String name;
    private String email;
    private List<Address> addressList;

}
