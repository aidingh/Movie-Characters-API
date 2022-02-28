package com.example.moviecharactersapi.models;

import javax.persistence.*;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    public String name;

    @Column
    public String description;

}
