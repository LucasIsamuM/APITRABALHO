package com.trabalho.crud.core.service;

import com.trabalho.crud.core.entity.BusinessException;
import com.trabalho.crud.core.entity.Quarto;
import com.trabalho.crud.core.repository.QuartoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuartoServiceTest {

    @Mock
    private QuartoRepository quartoRepository;

    @InjectMocks
    private QuartoService quartoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodosOsQuartos() {
        List<Quarto> mockList = Arrays.asList(
                new Quarto(1L, 101, true),
                new Quarto(2L, 202, false)
        );

        when(quartoRepository.findAll()).thenReturn(mockList);

        List<Quarto> resultado = quartoService.findAll();

        assertEquals(2, resultado.size());
        assertEquals(101, resultado.get(0).getNumeroQuarto());
        assertEquals(false, resultado.get(1).isDisponivel());
        verify(quartoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarQuartoPorId() {
        Quarto quarto = new Quarto(1L, 101, true);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        Quarto resultado = quartoService.findById(1L);

        assertEquals(101, resultado.getNumeroQuarto());
        assertTrue(resultado.isDisponivel());
    }

    @Test
    void deveLancarExcecaoSeQuartoNaoExistir() {
        when(quartoRepository.findById(99L)).thenReturn(Optional.empty());

        BusinessException ex = assertThrows(BusinessException.class,
                () -> quartoService.findById(99L));

        assertEquals("Quarto não encontrado", ex.getMessage());
    }

    @Test
    void deveSalvarQuarto() {
        Quarto quarto = new Quarto(null, 303, true);
        when(quartoRepository.save(quarto)).thenReturn(new Quarto(1L, 303, true));

        Quarto salvo = quartoService.save(quarto);

        assertNotNull(salvo.getId());
        assertEquals(303, salvo.getNumeroQuarto());
        verify(quartoRepository, times(1)).save(quarto);
    }

    @Test
    void deveAtualizarQuarto() {
        Quarto antigo = new Quarto(1L, 101, true);
        Quarto novo = new Quarto(1L, 404, false);

        when(quartoRepository.findById(1L)).thenReturn(Optional.of(antigo));
        when(quartoRepository.save(novo)).thenReturn(novo);

        Quarto atualizado = quartoService.update(1L, novo);

        assertEquals(404, atualizado.getNumeroQuarto());
        assertFalse(atualizado.isDisponivel());
        verify(quartoRepository, times(1)).save(novo);
    }

    @Test
    void deveExcluirQuartoPorId() {
        Quarto quarto = new Quarto(1L, 101, true);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        quartoService.deleteById(1L);

        verify(quartoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveVerificarSeQuartoEstaDisponivel() {
        Quarto quarto = new Quarto(1L, 101, true);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quarto));

        boolean disponivel = quartoService.isDisponivel(1L);

        assertTrue(disponivel);
    }

    @Test
    void deveAtualizarDisponibilidade() {
        Quarto quartoExistente = new Quarto(1L, 101, true);
        when(quartoRepository.findById(1L)).thenReturn(Optional.of(quartoExistente));
        when(quartoRepository.save(any(Quarto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Quarto resultado = quartoService.atualizarDisponibilidade(1L, false);

        assertFalse(resultado.isDisponivel());
        verify(quartoRepository, times(1)).findById(1L);
        verify(quartoRepository, times(1)).save(quartoExistente);
    }
}
