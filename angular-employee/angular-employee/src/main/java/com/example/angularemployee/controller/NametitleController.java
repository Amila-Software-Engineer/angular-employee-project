package com.example.angularemployee.controller;

import com.example.angularemployee.dao.NametitleDao;
import com.example.angularemployee.entity.Nametitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nametitles")
@CrossOrigin
public class NametitleController {
    @Autowired
    private NametitleDao nametitleDao;

    @GetMapping
    public List<Nametitle> getAll(){
        return nametitleDao.findAll();
    }

    @GetMapping("/{id}")
    public Nametitle get(@PathVariable Integer id){
        Optional<Nametitle> optionalNametitle = nametitleDao.findById(id);

        if(optionalNametitle.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nametitle Not Found.");
        return optionalNametitle.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!nametitleDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nametitle Not Found.");
        nametitleDao.deleteById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Nametitle add(@RequestBody Nametitle nametitle){
        return nametitleDao.save(nametitle);
    }
    
    @PutMapping("/{id}")
    public Nametitle update(@PathVariable Integer id, @RequestBody Nametitle nametitle){
        if(!nametitleDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nametitle not found.");
        
        nametitle.setId(id);
        return nametitleDao.save(nametitle);
    }

}
