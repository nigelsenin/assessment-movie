package com.assessment.movie.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.movie.model.MovieGenre;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
	
	@Query("select u from MovieGenre u where u.movId= :movId ")
	public List<MovieGenre> findByMovId(@Param("movId") Long movId);
	
	@Transactional
	@Modifying
	@Query("delete from MovieGenre u where u.movId= :movId ")
	public void deleteByMovId(@Param("movId") Long movId);

}
