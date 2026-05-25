package com.ipartek.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipartek.modelo.Coche;

public interface CocheRepositorio extends JpaRepository<Coche, Integer> {

    List<Coche> findAllByOrderByPrecioAsc();
}