package com.trabalho.crud.core.repository;

import java.util.List;
import java.util.Optional;

import com.trabalho.crud.core.entity.Quarto;

public interface QuartoRepository {

	List<Quarto> findAll();

	Optional<Quarto> findById(Long id);

	Quarto save(Quarto Quarto);

	void deleteById(Long id);

}
