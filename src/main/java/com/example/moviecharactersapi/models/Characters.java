package com.example.moviecharactersapi.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Characters {
    @Override
    public String toString() {
        return "Characters{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", alias='" + alias + '\'' +
                ", gender='" + gender + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", movies=" + movies +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column
    public String fullName;

    @Column
    public String alias;

    @Column
    public String gender;

    @Column
    public String pictureUrl;

    @Nullable
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    List<Movie> movies;
}
