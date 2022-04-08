package com.delfsoftware.domain.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Categorias {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
}
