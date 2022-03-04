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
public class Movie {
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieTitle='" + movieTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", director='" + director + '\'' +
                ", picture='" + picture + '\'' +
                ", trailerLink='" + trailerLink + '\'' +
                ", characters=" + characters +
                ", franchise=" + franchise +
                '}';
    }

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
    //@ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @ManyToMany
    @JoinTable(
            name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "characters_id"))
    public List<Characters> characters;

    @Nullable
    @JsonGetter("characters")
    public List<String> characters(){
        return characters.stream().
                map(character -> "/read/character/"+ character.getId()).collect(Collectors.toList());
    }

    /**
     * Franchise table that belong to each movie entity
     */
    @Nullable
    @ManyToOne
    @JoinColumn(name="franchise_id")
    public Franchise franchise;

    /*@JsonGetter("franchise")
    public String franchise() {
        return franchise != null ? "/read/franchise/" + franchise.getId() : null;
    }*/

    public List<Characters> getCharacters() {
        return characters;
    }

}
