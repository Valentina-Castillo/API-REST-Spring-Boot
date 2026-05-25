package com.ipartek.servicios;

import java.util.List;

import com.ipartek.modelo.Usuario;

public interface UsuarioServicio {

	public List<Usuario> obtenerTodosUsuarios();
	public Usuario obtenerUsuarioPorId(Integer id);
	public Usuario guardarUsuario(Usuario usu);
	public Usuario modificarUsuario(Usuario usu);
	public Boolean borrarUsuario(Integer id);
	public String validarUsuario(Usuario usu);
}
