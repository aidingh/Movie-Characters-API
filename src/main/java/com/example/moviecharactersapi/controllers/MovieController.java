package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MovieController {
    HttpStatus httpStatus;

    @Autowired
    public MovieRepository movieRepository;

    @Autowired
    public CharacterRepository characterRepository;

    @PostMapping("/create/movie")
    public Movie createMovie(@RequestBody Movie movie)
    {
        return movieRepository.save(movie);
    }

    @GetMapping("/read/all/movie")
    public List<Movie> readAllMovies()
    {
      //  return movieRepository.findAll();
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }
    
    @GetMapping("/read/all/characterInMovie/{id}")
    public ResponseEntity <List<Characters>> readAllCharactersInMovie(@PathVariable Integer id)
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


    @GetMapping("/update/movie")
    public Movie updateCharacterById(@RequestBody Movie movie)
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


    @GetMapping("/read/movie/{id}")
    public Movie getMovieById(@PathVariable Integer id)
    {
        if(movieRepository.existsById(id)) {
            return movieRepository.findById(id).get();
        }
        return null;
    }

    @DeleteMapping("/delete/movie/{id}")
    public boolean deleteMovieById(@PathVariable Integer id)
    {
        if(this.getMovieById(id) != null){
            movieRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    @PostMapping("/update/character/movie/{movie_id}")
    public void updateCharactersInMovies(@PathVariable Integer movie_id, @RequestBody Integer[] characterId)
    {
        Movie movie = movieRepository.findById(movie_id).get();

       for(int ids : characterId){
           Characters characterTmp = characterRepository.findById(ids).get();
           movie.characters.add(characterTmp);
       }
       movieRepository.save(movie);
    }
}
