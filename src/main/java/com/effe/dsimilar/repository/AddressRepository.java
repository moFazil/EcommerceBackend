package com.effe.dsimilar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effe.dsimilar.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
