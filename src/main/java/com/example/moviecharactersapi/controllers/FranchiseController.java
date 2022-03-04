package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FranchiseController {

    HttpStatus httpStatus;

    @Autowired
    public FranchiseRepository franchiseRepository;
    @Autowired
    public MovieRepository movieRepository;

    public EntityManager entityManager;


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

    @GetMapping("/read/all/moviesInFranchise/{id}")
    public ResponseEntity<List<Movie>> readAllMoviesInFranchise(@PathVariable Integer id)
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
     * @param franchise_id is a specific franchise id from the franchise entity
     * @return a list of a characters in franchise
     */
    @GetMapping("/read/all/characterInFranchise/{franchise_id}")
    public ResponseEntity<List<Characters>> readAllCharactersInFranchise(@PathVariable Integer franchise_id)
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

    @PostMapping("/update/franchise/{franchiseId}")
    public void updateMoviesInFranchises(@PathVariable Integer franchiseId, @RequestBody Integer[] movieIds)
    {
        Franchise tempFranchise = franchiseRepository.findById(franchiseId).get();
        ArrayList<Movie> moviesList = new ArrayList<>();
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

    }
}
