package com.delfsoftware.domain.modelo.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoriaForm {

	 @NotBlank
	  private String nome;
}
