package com.sha.student.client;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressClient addressClient;

    @Transactional
    public List<Address> retrieveAllAddresses(Long studentId) {
        return addressClient.getAllAddresses(studentId);
    }
}
