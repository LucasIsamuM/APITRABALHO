package com.trabalho.crud.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trabalho.crud.core.entity.Quarto;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
}
