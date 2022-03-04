package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public String name;

    @Column
    public String description;

    @Nullable
    @OneToMany(mappedBy="franchise")
    public List<Movie> movies;

    @JsonGetter("movies")
    public List<String> movies(){
        return movies.stream().map(movie -> "/read/movie/"+ movie.getId()).collect(Collectors.toList());
    }
}
