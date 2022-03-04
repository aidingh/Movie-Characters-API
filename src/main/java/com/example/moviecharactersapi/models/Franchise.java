package com.example.moviecharactersapi.models;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Franchise {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Movie> getMovieFranchise() {
        return movieFranchise;
    }

    public void setMovieFranchise(Set<Movie> movieFranchise) {
        this.movieFranchise = movieFranchise;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public String name;

    @Column
    public String description;

    @Nullable
    @OneToMany(mappedBy="franchise")
    public Set<Movie> movieFranchise;


}
