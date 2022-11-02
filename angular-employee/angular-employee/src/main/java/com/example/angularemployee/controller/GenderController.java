package com.example.angularemployee.controller;

import com.example.angularemployee.dao.GenderDao;
import com.example.angularemployee.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genders")
@CrossOrigin
public class GenderController {
    @Autowired
    private GenderDao genderDao;

    @GetMapping
    public List<Gender> getAll(){
        return genderDao.findAll();
    }

    @GetMapping("/{id}")
    public Gender get(@PathVariable Integer id){
        Optional<Gender> optionalGender = genderDao.findById(id);

        if(optionalGender.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender Not Found.");
        return optionalGender.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!genderDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender Not Found.");
        genderDao.deleteById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gender add(@RequestBody Gender gender){
        return genderDao.save(gender);
    }

    @PutMapping("/{id}")
    public Gender update(@PathVariable Integer id, @RequestBody Gender gender){
        if(!genderDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender not found.");

        gender.setId(id);
        return genderDao.save(gender);
    }

}
