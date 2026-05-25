package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Coche;

public interface CocheServicio {

    List<Coche> listarTodos();

    Coche guardar(Coche coche);

    Coche modificar(Coche coche);

    boolean eliminar(Integer id);

    Coche buscarPorId(Integer id);

    List<Coche> listarOrdenadosPorPrecio();
}