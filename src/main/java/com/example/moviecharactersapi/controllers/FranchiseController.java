package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FranchiseController {

    @Autowired
    public FranchiseRepository franchiseRepository;

    @PostMapping("/create/franchise")
    public Franchise createFranchise(@RequestBody Franchise franchise)
    {
        return franchiseRepository.save(franchise);
    }

    @GetMapping("/read/all/franchise")
    public List<Franchise> readAllFranchises()
    {
        return franchiseRepository.findAll();
    }

    @GetMapping("/read/franchise/{id}")
    public Franchise getFranchiseById(@PathVariable Integer id)
    {
        if(franchiseRepository.existsById(id)) {
            return franchiseRepository.findById(id).get();
        }
        return null;
    }

    @GetMapping("/update/franchise")
    public Franchise updateFranchiseById(@RequestBody Franchise franchise)
    {
        Franchise tempFranchise = getFranchiseById(franchise.id);
        tempFranchise.setName(franchise.name);
        tempFranchise.setDescription(franchise.description);
        return franchiseRepository.save(tempFranchise);
    }

    @DeleteMapping("/delete/franchise/{id}")
    public boolean deleteFranchiseById(@PathVariable Integer id)
    {
        if(this.getFranchiseById(id) != null){
            franchiseRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
