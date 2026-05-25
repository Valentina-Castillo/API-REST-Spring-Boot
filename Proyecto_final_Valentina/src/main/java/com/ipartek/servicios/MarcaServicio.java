package com.ipartek.servicios;

import java.util.List;
import com.ipartek.modelo.Marca;

public interface MarcaServicio {
	
    List<Marca> listarTodas();
    Marca buscarPorId(Integer id);
    Marca guardar(Marca marca);
    Marca modificar(Marca marca);
    boolean eliminar(Integer id);
}