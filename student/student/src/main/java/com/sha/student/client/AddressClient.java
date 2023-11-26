package com.sha.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ADDRESS-SERVICE", url = "${application.config.address-url}")
public interface AddressClient {

    @GetMapping("/student/{studentId}")
    List<Address> getAllAddresses(@PathVariable("studentId") Long studentId);
}

