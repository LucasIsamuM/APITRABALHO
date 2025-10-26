package com.trabalho.crud.outbound.repository;

import com.trabalho.crud.core.entity.Quarto;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.crud.core.repository.QuartoRepository;

@Profile("!test")
public interface JpaQuartoRepository extends QuartoRepository, JpaRepository<Quarto, Long> {

}
