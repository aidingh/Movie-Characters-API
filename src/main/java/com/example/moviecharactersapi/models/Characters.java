package com.example.moviecharactersapi.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Aidin Ghassemloi och Richard Cruz.
 * Entity class.
 * Class defines attributes within the entity and its relation to other entitys.
 */
@Entity
@Getter
@Setter
public class Characters {

    /**
     * Id attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Full name attribute.
     */
    @Column
    public String fullName;

    /**
     * Full alias attribute.
     */
    @Column
    public String alias;

    /**
     * Gender attribute.
     */
    @Column
    public String gender;

    /**
     * Picture url attribute.
     */
    @Column
    public String pictureUrl;

    /**
     * Defines a many-to-many relation with the Movie entity.
     */
    @Nullable
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    List<Movie> movies;

    /**
     * Override Javas default equals to compare attributes and not the objects themselves.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return id == that.id && Objects.equals(fullName, that.fullName) && Objects.equals(alias, that.alias) && Objects.equals(gender, that.gender) && Objects.equals(pictureUrl, that.pictureUrl) && Objects.equals(movies, that.movies);
    }

}
