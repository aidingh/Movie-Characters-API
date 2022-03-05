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
public class Movie {

    /**
     * Id attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Movie title attribute.
     */
    @Column
    public String movieTitle;

    /**
     * Movie genre attribute.
     */
    @Column
    public String genre;

    /**
     * Movie release year attribute.
     */
    @Column
    public String releaseYear;

    /**
     * Movie director attribute.
     */
    @Column
    public String director;

    /**
     * Movie picture attribute.
     */
    @Column
    public String picture;

    /**
     * Movie trailer link attribute.
     */
    @Column
    public String trailerLink;

    /**
     * Defines a many-to-many relation with the Characters entity.
     * A linking table is made called movie_characters.
     * It joins the columns by the ids.
     */
    @ManyToMany
    @JoinTable(
            name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "characters_id"))
    public List<Characters> characters;

    /**
     * Function will map and update records.
     */
    @Nullable
    @JsonGetter("characters")
    public List<String> characters(){
        return characters.stream().
                map(character -> "/read/character/"+ character.getId()).collect(Collectors.toList());
    }

    /**
     * Defines a many-to-one relation with the Franchise entity.
     */
    @Nullable
    @ManyToOne
    @JoinColumn(name="franchise_id")
    public Franchise franchise;

    public List<Characters> getCharacters() {
        return characters;
    }

}
