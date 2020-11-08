package com.assessment.movie.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "movie_genre")
@EntityListeners(AuditingEntityListener.class)
public class MovieGenre implements Serializable {

	private static final long serialVersionUID = 1967806040470477421L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movGenId;

	@NotBlank
	private Long movId;

	@NotBlank
	private Long genId;
	
	public MovieGenre() {
		
	}
	
	public MovieGenre(Long movId, Long genId) {
		this.movId = movId;
		this.genId = genId;
	}

	public Long getMovGenId() {
		return movGenId;
	}

	public void setMovGenId(Long movGenId) {
		this.movGenId = movGenId;
	}

	public Long getMovId() {
		return movId;
	}

	public void setMovId(Long movId) {
		this.movId = movId;
	}

	public Long getGenId() {
		return genId;
	}

	public void setGenId(Long genId) {
		this.genId = genId;
	}
	

}