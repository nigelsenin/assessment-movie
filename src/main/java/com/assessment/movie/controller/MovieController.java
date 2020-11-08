package com.assessment.movie.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.movie.exception.InvalidRequestException;
import com.assessment.movie.exception.ResourceNotFoundException;
import com.assessment.movie.model.Movie;
import com.assessment.movie.model.MovieGenre;
import com.assessment.movie.repository.GenreRepository;
import com.assessment.movie.repository.MovieGenreRepository;
import com.assessment.movie.repository.MovieRepository;

@RestController
@RequestMapping("/assessment/api/v1/")
public class MovieController {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	MovieGenreRepository movieGenreRepository;

	private static List<Double> ratingList = Arrays.asList(0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0);

	/**
	 * Get all movies
	 * 
	 * @return movieList
	 */
	@GetMapping("/movies")
	public List<Movie> getAllMovies() {

		List<Movie> movieList = movieRepository.findAll();
		if (movieList != null && movieList.size() > 0) {
			movieList.forEach(mov -> {
				List<MovieGenre> movieGenreList = movieGenreRepository.findByMovId(mov.getMovId());
				if (movieGenreList != null && movieGenreList.size() > 0) {
					mov.setMovieGenreList(movieGenreList);
				}
			});
		}
		return movieList;
	}

	/**
	 * Create a new movie
	 * 
	 * @param movie
	 * @return Movie
	 */
	@PostMapping("/movies")
	public Movie createMovie(@Valid @RequestBody Movie movie) {

		if (!ratingList.contains(movie.getMovRating())) {
			throw new InvalidRequestException("mov_rating", movie.getMovRating());
		}

		Movie movObj = movieRepository.save(movie);
		List<MovieGenre> movGenList = new ArrayList<MovieGenre>();
		if (!movie.getMovieGenreList().isEmpty()) {
			movie.getMovieGenreList().forEach(mg -> {
				MovieGenre movGen = new MovieGenre(movObj.getMovId(), mg.getGenId());
				movieGenreRepository.save(movGen);
				movGenList.add(movGen);
			});
		}
		movObj.setMovieGenreList(movGenList);
		return movObj;
	}

	/**
	 * Get a movie by id
	 * 
	 * @param noteId
	 * @return Movie
	 */
	@GetMapping("/movies/{id}")
	public Movie getMovieById(@PathVariable(value = "id") Long movId) {

		Movie movie = movieRepository.findById(movId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movId));

		List<MovieGenre> movieGenreList = movieGenreRepository.findByMovId(movId);
		if (movieGenreList != null && movieGenreList.size() > 0) {
			movie.setMovieGenreList(movieGenreList);
		}

		return movie;
	}

	/**
	 * Update a movie
	 * 
	 * @param movId
	 * @param movieDetails
	 * @return Movie
	 */
	@PutMapping("/movies/{id}")
	public Movie updateMovie(@PathVariable(value = "id") Long movId, @Valid @RequestBody Movie movie) {

		if (!ratingList.contains(movie.getMovRating())) {
			throw new InvalidRequestException("mov_rating", movie.getMovRating());
		}

		List<MovieGenre> movGenList = new ArrayList<MovieGenre>();
		Movie movObj = getMovieById(movId);

		movObj.setMovTitle(movie.getMovTitle());
		movObj.setMovRating(movie.getMovRating());
		movieRepository.save(movObj);

		if (movie.getMovieGenreList() != null && movie.getMovieGenreList().size() > 0) {
			movie.getMovieGenreList().forEach(mg -> {
				genreRepository.findById(mg.getGenId())
						.orElseThrow(() -> new ResourceNotFoundException("Genre", "id", mg.getGenId()));
			});

			movieGenreRepository.deleteByMovId(movId);

			movie.getMovieGenreList().forEach(mg -> {
				MovieGenre movGen = new MovieGenre(movId, mg.getGenId());
				movieGenreRepository.save(movGen);
				movGenList.add(movGen);
			});
		}
		movObj.setMovieGenreList(movGenList);
		return movObj;
	}

	/**
	 * Delete a movie
	 * 
	 * @param movId
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable(value = "id") Long movId) {
		Movie movie = movieRepository.findById(movId)
				.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movId));

		movieRepository.delete(movie);
		movieGenreRepository.deleteByMovId(movId);
		return ResponseEntity.ok().build();
	}
}