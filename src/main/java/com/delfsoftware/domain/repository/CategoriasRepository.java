package com.delfsoftware.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delfsoftware.domain.modelo.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long>{

}
