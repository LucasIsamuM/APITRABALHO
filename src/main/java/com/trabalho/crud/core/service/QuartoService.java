package com.trabalho.crud.core.service;

import java.util.List;

import com.trabalho.crud.core.entity.Quarto;
import org.springframework.stereotype.Service;

import com.trabalho.crud.core.entity.BusinessException;
import com.trabalho.crud.core.repository.QuartoRepository;

@Service
public class QuartoService {

	private final QuartoRepository repository;


	public QuartoService(QuartoRepository quartoRepository){
		this.repository = quartoRepository;
	}

	public List<Quarto> findAll() {
		return repository.findAll();
	}

	public Quarto findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> BusinessException.notFoundException("Usuário não encontrado"));
	}

	public Quarto save(Quarto Quarto) {
		return repository.save(Quarto);
	}

	public Quarto update(Long id, Quarto Quarto) {
		var existingUser = this.findById(id);
		Quarto.setId(existingUser.getId());
		return repository.save(Quarto);
	}

	public void deleteById(Long id) {
		this.findById(id);
		repository.deleteById(id);
	}

}
