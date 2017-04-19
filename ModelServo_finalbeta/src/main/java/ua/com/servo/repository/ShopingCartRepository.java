package ua.com.servo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.servo.entity.ShopingCart;

public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer>{

}