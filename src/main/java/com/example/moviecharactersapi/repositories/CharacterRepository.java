package com.example.moviecharactersapi.repositories;
import com.example.moviecharactersapi.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Characters, Integer> {
}
