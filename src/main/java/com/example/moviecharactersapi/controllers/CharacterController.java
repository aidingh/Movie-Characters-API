package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Aidin Ghassemloi och Richard Cruz.
 * RestController class is responseble for handeling client requests.
 * Class instanciates the repositories needed to do CRUD operations and more.
 */

@RestController
public class CharacterController {

    @Autowired
    public CharacterRepository characterRepository;

    /**
     * Function will create a new character in the database.
     *
     * @param character JSON-object defined as a Characters object.
     * @return Characters as a JSON-object.
     */
    @Operation(summary = "Create a characters.")
    @PostMapping("/create/character")
    public Characters createCharacter(@Parameter(description = "Character object defined as a JSON-body.") @RequestBody Characters character)
    {
         return characterRepository.save(character);
    }

    /**
     * Function will read all characters from the database.
     *
     * @return List<Characters> List of movies as a JSON-object.
     */
    @Operation(summary = "Read all characters.")
    @GetMapping("/read/all/character")
    public List<Characters> readAllCharacters()
    {
        return characterRepository.findAll();
    }

    /**
     * Function will read an existing character by id in the database.
     *
     * @param id defined as a path variable.
     * @return Characters as a JSON-object.
     */
    @Operation(summary = "Read character by id.")
    @GetMapping("/read/character/{id}")
    public Characters getCharacterById(@Parameter(description = "Enter Id to find a character.") @PathVariable Integer id)
    {
        if(characterRepository.existsById(id)) {
            return characterRepository.findById(id).get();
        }
        return null;
    }

    /**
     * Function will update an existing character by id in the database.
     *
     * @param character defined as a request bode. Search id is contained within this body.
     * @return Characters as a JSON-object.
     */
    @Operation(summary = "Update character by id.")
    @GetMapping("/update/character")
    public Characters updateCharacterById(@Parameter(description = "Character object defined as a JSON-body.") @RequestBody Characters character)
    {
        Characters tempChar = getCharacterById(character.id);
        tempChar.setAlias(character.alias);
        tempChar.setFullName(character.fullName);
        tempChar.setGender(character.gender);
        tempChar.setPictureUrl(character.pictureUrl);
        return characterRepository.save(tempChar);
    }

    /**
     * Function will delete an existing character by id in the database.
     *
     * @param id defined as a path variable.
     * @return true if character is deleted else the result is false.
     */
    @Operation(summary = "Delete a character by id.")
    @DeleteMapping("/delete/character/{id}")
    public boolean deleteCharacterById(@Parameter(description = "Enter Id to delete a character.") @PathVariable Integer id)
    {
        if(this.getCharacterById(id) != null){
             characterRepository.deleteById(id);
             return true;
        }
        else{
            return false;
        }
    }

}
