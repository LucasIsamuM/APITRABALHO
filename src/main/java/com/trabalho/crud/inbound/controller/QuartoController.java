package com.trabalho.crud.inbound.controller;

import java.util.List;

import com.trabalho.crud.core.entity.Quarto;
import com.trabalho.crud.core.service.QuartoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Quarto")
public class QuartoController {

	private final QuartoService quartoService;

	public QuartoController(QuartoService quartoService) {
		this.quartoService = quartoService;
	}

	@GetMapping
	public ResponseEntity<List<Quarto>> getAllUsers() {
		return ResponseEntity.ok(quartoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Quarto> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(quartoService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Quarto> createUser(@RequestBody Quarto Quarto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(quartoService.save(Quarto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Quarto> updateUser(@PathVariable Long id, @RequestBody Quarto Quarto) {
		return ResponseEntity.ok(quartoService.update(id, Quarto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		quartoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
