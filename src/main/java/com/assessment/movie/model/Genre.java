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
@Table(name = "genre")
@EntityListeners(AuditingEntityListener.class)
public class Genre implements Serializable {

	private static final long serialVersionUID = 165739554059921693L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genId;

	@NotBlank
	private String genName;

	public Long getGenId() {
		return genId;
	}

	public void setGenId(Long genId) {
		this.genId = genId;
	}

	public String getGenName() {
		return genName;
	}

	public void setGenName(String genName) {
		this.genName = genName;
	}

}