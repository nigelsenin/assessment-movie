package com.assessment.movie.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "movie")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createDt", "updateDt" }, allowGetters = true)
public class Movie implements Serializable {

	private static final long serialVersionUID = -9016188038913874168L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movId;

	@NotBlank
	private String movTitle;

	@NotBlank
	private Double movRating;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createDt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateDt;
	
	@Transient
	private List<MovieGenre> movieGenreList;
	
	public Movie() {
	}
	
	public Movie(Long movId, String movTitle, Double movRating, List<MovieGenre> movieGenreList) {
		this.movId = movId;
		this.movTitle = movTitle;
		this.movRating = movRating;
		this.movieGenreList = movieGenreList;
	}
	
	public Long getMovId() {
		return movId;
	}
	
	public void setMovId(Long movId) {
		this.movId = movId;
	}

	public String getMovTitle() {
		return movTitle;
	}

	public void setMovTitle(String movTitle) {
		this.movTitle = movTitle;
	}

	public Double getMovRating() {
		return movRating;
	}

	public void setMovRating(Double movRating) {
		this.movRating = movRating;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public List<MovieGenre> getMovieGenreList() {
		return movieGenreList;
	}

	public void setMovieGenreList(List<MovieGenre> movieGenreList) {
		this.movieGenreList = movieGenreList;
	}

}