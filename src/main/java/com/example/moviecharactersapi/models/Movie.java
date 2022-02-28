package com.example.moviecharactersapi.models;

import javax.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int movie_id;

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
}
