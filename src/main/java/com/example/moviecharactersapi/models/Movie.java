package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String movieTitle;

    @Column
    public String genre;

    @Column
    public String releaseYear;

    @Column
    public String director;

    @Column
    public String picture;

    @Column
    public String trailerLink;
    /**
     * Creates a middle table between movie table and character table
     */
    @Nullable
    @ManyToMany
    @JoinTable(
            name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "characters_id"))
    Set<Characters> movieCharacter;

    @Nullable
    @ManyToOne
    @JoinColumn(name="franchise_id")
    private Franchise franchise;

    @Nullable
    @ManyToMany
    public List<Characters> characters;

    @Nullable
    @JsonGetter("characters")
    public List<String> characters(){
        return characters.stream().
                map(character -> "/read/character/"+ character.getId()).collect(Collectors.toList());
    }
}
