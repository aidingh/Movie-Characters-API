package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

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
    public List<Characters> readAllCharactersInMovie(@PathVariable Integer id)
    {

        List<Characters> characters = null;

        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.findById(id).get();
            characters = movie.getCharacters();
        }

          return characters;
    }

    @PostMapping("/update/character/movie/{id}")
    public void updateCharactersInMovies(@PathVariable Integer id, @RequestBody List<Integer> characterId)
    {

      Movie movie = movieRepository.findById(id).get();

       for(int ids : characterId){
           Characters characterTmp = characterRepository.findById(ids).get();
           movie.characters.add(characterTmp);
       }
       movieRepository.save(movie);

    }
}
