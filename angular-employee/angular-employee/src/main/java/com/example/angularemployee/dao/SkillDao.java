package com.example.angularemployee.dao;


import com.example.angularemployee.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillDao extends JpaRepository<Skill, Integer> {
}
