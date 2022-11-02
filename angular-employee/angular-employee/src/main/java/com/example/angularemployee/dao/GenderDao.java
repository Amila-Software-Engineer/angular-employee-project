package com.example.angularemployee.dao;


import com.example.angularemployee.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDao  extends JpaRepository<Gender, Integer> {
}
