package com.ipartek.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ipartek.modelo.Marca;
import com.ipartek.repositorio.MarcaRepositorio;

@Service
public class MarcaServicioImp implements MarcaServicio {

    @Autowired
    private MarcaRepositorio marcaRepo;

    @Override
    public List<Marca> listarTodas() {
        return marcaRepo.findAll();
    }

    @Override
    public Marca buscarPorId(Integer id) {
        return marcaRepo.findById(id).orElse(null);
    }

    @Override
    public Marca guardar(Marca marca) {
        return marcaRepo.save(marca);
    }

    @Override
    public Marca modificar(Marca marca) {
        return marcaRepo.save(marca);
    }

    @Override
    public boolean eliminar(Integer id) {
    	
        // Comprobamos si existe antes de intentar borrar
        if (marcaRepo.existsById(id)) {
            marcaRepo.deleteById(id);
            return true;
        }
        return false;
    }
}