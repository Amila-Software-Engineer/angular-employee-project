package com.example.angularemployee.dao;


import com.example.angularemployee.entity.Nametitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NametitleDao extends JpaRepository<Nametitle, Integer> {
}
