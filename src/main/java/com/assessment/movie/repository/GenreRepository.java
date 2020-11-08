package com.assessment.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.movie.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
