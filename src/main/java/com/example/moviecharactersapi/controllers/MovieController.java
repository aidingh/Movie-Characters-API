package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Characters;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return movieRepository.findAll();
    }

    @PutMapping("/update/character/movie/{id}/characters")
    public void updateCharactersInMovies(@PathVariable Integer id, @RequestBody List<Integer> characterId)
    {
      // Ej klart
     Movie movie = movieRepository.findById(id).get();

 
    }
}
