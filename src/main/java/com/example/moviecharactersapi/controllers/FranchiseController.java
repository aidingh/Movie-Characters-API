package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aidin Ghassemloi och Richard Cruz.
 * RestController class is responseble for handeling client requests.
 * Class instanciates the repositories needed to do CRUD operations and more.
 */

@RestController
public class FranchiseController {

    HttpStatus httpStatus;

    @Autowired
    public FranchiseRepository franchiseRepository;

    @Autowired
    public MovieRepository movieRepository;

    /**
     * Function will create a new franchise in the database.
     *
     * @param franchise JSON-object defined as a Franchise object.
     * @return Franchise as a JSON-object.
     */
    @Operation(summary = "Create a franchise.")
    @PostMapping("/create/franchise")
    public Franchise createFranchise(@Parameter(description = "Franchise object defined as a JSON-body.") @RequestBody Franchise franchise)
    {
        return franchiseRepository.save(franchise);
    }

    /**
     * Function will read all franchises from the database.
     *
     * @return List<Franchise> List of movies as a JSON-object.
     */
    @Operation(summary = "Read all franchises.")
    @GetMapping("/read/all/franchise")
    public List<Franchise> readAllFranchises()
    {
        return franchiseRepository.findAll();
    }

    /**
     * Function will read an existing franchise by id in the database.
     *
     * @param id defined as a path variable.
     * @return Franchise as a JSON-object.
     */
    @Operation(summary = "Read franchise by id.")
    @GetMapping("/read/franchise/{id}")
    public Franchise getFranchiseById(@Parameter(description = "Enter Id to find a franchise.") @PathVariable Integer id)
    {
        if(franchiseRepository.existsById(id)) {
            return franchiseRepository.findById(id).get();
        }
        return null;
    }

    /**
     * Function will read all movies in a franchise.
     *
     * @param id defined as a path variable.
     * @return ResponseEntity<List<Movie>> a response entity object and its actual response: List<Movie>.
     */
    @Operation(summary = "Read all movies in a franchise.")
    @GetMapping("/read/all/moviesInFranchise/{id}")
    public ResponseEntity<List<Movie>> readAllMoviesInFranchise(@Parameter(description = "Enter a franchise id") @PathVariable Integer id)
    {
        List<Movie> moviesInFranchise = null;

        if(franchiseRepository.existsById(id)){
            Franchise franchise = franchiseRepository.findById(id).get();
            moviesInFranchise = franchise.getMovies();
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(moviesInFranchise, httpStatus);
    }


    /**
     * Function will read all characters in a franchise.
     *
     * @param franchise_id defined as a path variable.
     * @return ResponseEntity<List<Movie>> a response entity object and its actual response: List<Characters>.
     */
    @Operation(summary = "Read all characters in a franchise.")
    @GetMapping("/read/all/characterInFranchise/{franchise_id}")
    public ResponseEntity<List<Characters>> readAllCharactersInFranchise(@Parameter(description = "Enter a franchise id") @PathVariable Integer franchise_id)
    {
        List<Characters> characterInFranchise = new ArrayList<>();

        if(franchiseRepository.existsById(franchise_id)){
            Franchise franchise = franchiseRepository.findById(franchise_id).get();

            for(Movie movie: franchise.getMovies()){
                for(Characters characters : movie.getCharacters()){
                    if(!characterInFranchise.contains(characters)){
                        characterInFranchise.add(characters);
                    }
                }
            }
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(characterInFranchise, httpStatus);
    }

    /**
     * Function will update an existing franchise by id in the database.
     *
     * @param franchise defined as a request bode. Search id is contained within this body.
     * @return Franchise as a JSON-object.
     */
    @Operation(summary = "Update franchise by id.")
    @GetMapping("/update/franchise")
    public Franchise updateFranchiseById(@Parameter(description = "Franchise object defined as a JSON-body.") @RequestBody Franchise franchise)
    {
        Franchise tempFranchise = getFranchiseById(franchise.id);
        tempFranchise.setName(franchise.name);
        tempFranchise.setDescription(franchise.description);
        return franchiseRepository.save(tempFranchise);
    }

    /**
     * Function will delete an existing franchise by id in the database.
     *
     * @param id defined as a path variable.
     * @return true if franchise is deleted else the result is false.
     */
    @Operation(summary = "Delete a franchise by id.")
    @DeleteMapping("/delete/franchise/{id}")
    public boolean deleteFranchiseById(@Parameter(description = "Enter Id to delete a franchise.") @PathVariable Integer id)
    {
        if(this.getFranchiseById(id) != null){
            franchiseRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Function will update movies in franchise.
     *
     * @param franchiseId defined as a path variable.
     * @return true if operation was successful else false.
     */
    @Operation(summary = "Update movies in a franchise.")
    @PostMapping("/update/franchise/{franchiseId}")
    public boolean updateMoviesInFranchises(@Parameter(description = "Enter franchise id and a array of movie ids in the JSON-body.") @PathVariable Integer franchiseId, @RequestBody Integer[] movieIds)
    {
        ArrayList<Movie> moviesList = new ArrayList<>();

        if(franchiseRepository.existsById(franchiseId)){
            Franchise tempFranchise = franchiseRepository.findById(franchiseId).get();

            for(int ids : movieIds){
                if(movieRepository.existsById(ids)) {
                    Movie movieTemp = movieRepository.findById(ids).get();
                    moviesList.add(movieTemp);
                    movieTemp.setFranchise(tempFranchise);
                    movieRepository.save(movieTemp);
                }
            }
            tempFranchise.setMovies(moviesList);
            franchiseRepository.save(tempFranchise);
            return true;
        }
        return false;
    }
}
