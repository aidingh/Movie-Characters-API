package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aidin Ghassemloi och Richard Cruz.
 * Repository interface.
 * Interface extends JpaRepository interface that provides CRUD-operations and more.
 */
public interface MovieRepository extends JpaRepository<Movie, Integer>{
}
