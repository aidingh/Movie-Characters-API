package com.example.moviecharactersapi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int char_id;

    @Column
    public String fullName;

    @Column
    public String alias;

    @Column
    public String gender;

    @Column
    public String pictureUrl;

}
