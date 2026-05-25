package com.ipartek.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.modelo.Usuario;
import java.util.List;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	public Usuario findByUser(String user);
	
	
}
