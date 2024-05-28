package com.foodDelivery.FoodDelivery.repository;

import com.foodDelivery.FoodDelivery.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
