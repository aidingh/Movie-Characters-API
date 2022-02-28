package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class CharacterController {

    @Autowired
    public CharacterRepository characterRepository;

    @PostMapping("/create/character")
    public Characters createCharacter(@RequestBody Characters character)
    {
         return characterRepository.save(character);
    }

    @GetMapping("/read/all/character")
    public List<Characters> readAllCharacters()
    {
        return characterRepository.findAll();
    }

    @GetMapping("/read/character/{id}")
    public Characters getCharacterById(@PathVariable Integer id)
    {
        if(characterRepository.existsById(id)) {
            return characterRepository.findById(id).get();
        }
        return null;
    }
}
