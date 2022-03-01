package com.example.moviecharactersapi.models;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    public String name;

    @Column
    public String description;

    @Nullable
    @OneToMany(mappedBy="franchise")
    public Set<Movie> movieFranchise;


}
