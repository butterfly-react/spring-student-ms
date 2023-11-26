package com.sha.address.repository;

import com.sha.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM address WHERE user_id = :userId", nativeQuery = true)
    List<Address> findAllAddressesByUserIdNativeQuery(@Param("userId") Long userId);
}