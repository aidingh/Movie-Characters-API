package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aidin Ghassemloi och Richard Cruz.
 * Entity class.
 * Class defines attributes within the entity and its relation to other entitys.
 */
@Getter
@Setter
@Entity
public class Franchise {

    /**
     * Id attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    /**
     * Franchise name attribute.
     */
    @Column
    public String name;

    /**
     * Franchise description attribute.
     */
    @Column
    public String description;

    /**
     * Defines a one-to-many relation with the Movie entity.
     */
    @Nullable
    @OneToMany(mappedBy="franchise")
    public List<Movie> movies;

    /**
     * Function will map and update records.
     */
    @JsonGetter("movies")
    public List<String> movies(){
        return movies.stream().map(movie -> "/read/movie/"+ movie.getId()).collect(Collectors.toList());
    }
}
