package com.sha.address.service;

import com.sha.address.model.Address;
import com.sha.address.repository.AddressRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;


    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }


    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }


    public Address updateAddress(Long id, Address updatedAddress) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (existingAddress.isPresent()) {
            Address addressToUpdate = existingAddress.get();

            addressToUpdate.setExactAddress(updatedAddress.getExactAddress());

            return addressRepository.save(addressToUpdate);
        }
        return null;
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public List<Address> getAllAddressesByStudentId(Long studentId) {

       return addressRepository.findAllAddressesByUserIdNativeQuery(studentId);
    }
}

