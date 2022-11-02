package com.example.angularemployee.controller;

import com.example.angularemployee.dao.SkillDao;
import com.example.angularemployee.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
@CrossOrigin
public class SkillController {
    @Autowired
    private SkillDao skillDao;

    @GetMapping
    public List<Skill> getAll(){
        return skillDao.findAll();
    }

    @GetMapping("/{id}")
    public Skill get(@PathVariable Integer id){
        Optional<Skill> optionalSkill = skillDao.findById(id);

        if(optionalSkill.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill Not Found.");
        return optionalSkill.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!skillDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill Not Found.");
        skillDao.deleteById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Skill add(@RequestBody Skill skill){
        return skillDao.save(skill);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Integer id, @RequestBody Skill skill){
        if(!skillDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found.");

        skill.setId(id);
        return skillDao.save(skill);
    }

}
