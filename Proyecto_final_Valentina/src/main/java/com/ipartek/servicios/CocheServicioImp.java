package com.ipartek.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.modelo.Coche;
import com.ipartek.repositorio.CocheRepositorio;

@Service
public class CocheServicioImp implements CocheServicio {

    @Autowired
    private CocheRepositorio cocheRepo;

    @Override
    public List<Coche> listarTodos() {
        return cocheRepo.findAll();
    }

    @Override
    public Coche guardar(Coche coche) {
        return cocheRepo.save(coche);
    }

    @Override
    public Coche modificar(Coche coche) {

        if (cocheRepo.existsById(coche.getId())) {
            return cocheRepo.save(coche);
        }

        return null;
    }

    @Override
    public boolean eliminar(Integer id) {

        if (cocheRepo.existsById(id)) {
            cocheRepo.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Coche buscarPorId(Integer id) {
        return cocheRepo.findById(id).orElse(null);
    }

    @Override
    public List<Coche> listarOrdenadosPorPrecio() {
        return cocheRepo.findAllByOrderByPrecioAsc();
    }
}