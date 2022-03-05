package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * @author Aidin Ghassemloi och Richard Cruz.
 * RestController class is responseble for handeling client requests.
 * Class instanciates the repositories needed to do CRUD operations and more.
 */

@RestController
public class MovieController {

    HttpStatus httpStatus;

    @Autowired
    public MovieRepository movieRepository;

    @Autowired
    public CharacterRepository characterRepository;

    /**
     * Function will create a new movie in the database.
     *
     * @param movie JSON-object defined as a Movie object, with the same attributes.
     * @return Movie as a JSON-object.
     */
    @Operation(summary = "Create a movie.")
    @PostMapping("/create/movie")
    public Movie createMovie(@Parameter(description = "Movie object defined as a JSON-body.") @RequestBody Movie movie)
    {
        return movieRepository.save(movie);
    }

    /**
     * Function will read all movies from the database.
     *
     * @return List<Movie> List of movies as a JSON-object.
     */
    @Operation(summary = "Read all movies.")
    @GetMapping("/read/all/movie")
    public List<Movie> readAllMovies()
    {
       return movieRepository.findAll();
    }

    /**
     * Function will read all characters in movie.
     *
     * @param id defined as a path variable.
     * @return ResponseEntity<List<Characters>> a response entity object and its actual response: List<Characters>.
     */
    @Operation(summary = "Read all characters in movies.")
    @GetMapping("/read/all/characterInMovie/{id}")
    public ResponseEntity<List<Characters>> readAllCharactersInMovie(@Parameter(description = "Enter Id to find a character in movies.") @PathVariable Integer id)
    {
        List<Characters> charactersInMovie = null;

        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.findById(id).get();
            charactersInMovie = movie.getCharacters();
            httpStatus = HttpStatus.OK;
            } else {
            httpStatus = HttpStatus.NO_CONTENT;
            }
          return new ResponseEntity<>(charactersInMovie, httpStatus);
    }

    /**
     * Function will update an existing movie in the database.
     *
     * @param movie JSON-object defined as a Movie object.
     * @return Movie as a JSON-object.
     */
    @Operation(summary = "Update movie by id. (Id is defined within JSON-body.)")
    @GetMapping("/update/movie")
    public Movie updateCharacterById(@Parameter(description = "Movie object defined as a JSON-body.") @RequestBody Movie movie)
    {
        Movie tempMovie = getMovieById(movie.id);
        tempMovie.setMovieTitle(movie.movieTitle);
        tempMovie.setGenre(movie.genre);
        tempMovie.setReleaseYear(movie.releaseYear);
        tempMovie.setDirector(movie.director);
        tempMovie.setPicture(movie.picture);
        tempMovie.setTrailerLink(movie.trailerLink);
        return movieRepository.save(tempMovie);
    }

    /**
     * Function will read an existing movie in the database.
     *
     * @param id defined as a path variable.
     * @return Movie as a JSON-object.
     */
    @Operation(summary = "Read movie by id.")
    @GetMapping("/read/movie/{id}")
    public Movie getMovieById(@Parameter(description = "Enter Id to read a movie.") @PathVariable Integer id)
    {
        if(movieRepository.existsById(id)) {
            return movieRepository.findById(id).get();
        }
        return null;
    }

    /**
     * Function will delete an existing movie in the database.
     *
     * @param id defined as a path variable.
     * @return true if movie is deleted else the result is false.
     */
    @Operation(summary = "Delete a movie by id.")
    @DeleteMapping("/delete/movie/{id}")
    public boolean deleteMovieById(@Parameter(description = "Enter Id to delete a movie.") @PathVariable Integer id)
    {
        if(this.getMovieById(id) != null){
            movieRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Function will update characters in movies.
     *
     * @param movie_id defined as a path variable.
     * @param characterId defined as a request body.
     * @return true if movie operation is made, else the result is false.
     */
    @Operation(summary = "Update characters in movies.")
    @PostMapping("/update/character/movie/{movie_id}")
    public boolean updateCharactersInMovies(@Parameter(description = "Enter movie id and a list character ids as a list.") @PathVariable Integer movie_id, @RequestBody Integer[] characterId)
    {
        if(movieRepository.existsById(movie_id)){
            Movie movie = movieRepository.findById(movie_id).get();

            for(int ids : characterId){
                Characters characterTmp = characterRepository.findById(ids).get();
                movie.characters.add(characterTmp);
            }
            movieRepository.save(movie);
            return true;
        }
        return false;
    }

}
