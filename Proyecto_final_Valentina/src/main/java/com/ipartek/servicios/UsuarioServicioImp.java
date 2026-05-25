package com.ipartek.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.auxiliar.Auxiliar;
import com.ipartek.componente.JwtUtil;
import com.ipartek.modelo.Usuario;
import com.ipartek.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public List<Usuario> obtenerTodosUsuarios() {

		try {
			return usuarioRepo.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario obtenerUsuarioPorId(Integer id) {
		try {
			return usuarioRepo.findById(id).orElse(new Usuario());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario guardarUsuario(Usuario usu) {
		try {
			if (usu.getId() == 0) {
				usu.setSalt(Auxiliar.generarHexRandom(16));
				usu.setPass(Auxiliar.hashear(usu.getPass() + usu.getSalt()));
				return usuarioRepo.save(usu);
			} else {
				return new Usuario();
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Usuario modificarUsuario(Usuario usu) {
		Usuario usuTemp = obtenerUsuarioPorId(usu.getId());

		if (usuTemp.getId() != 0) {
			usuTemp.setUser(usu.getUser());

			if (!usu.getPass().equals("")) {
				usuTemp.setPass(Auxiliar.hashear(usu.getPass() + usuTemp.getSalt()));
			}

			usuTemp.setRole(usu.getRole());
			
			usuarioRepo.save(usuTemp);
		}
		return usuTemp;
	}

	@Override
	public Boolean borrarUsuario(Integer id) {
		try {
			if (usuarioRepo.existsById(id)) {
				usuarioRepo.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String validarUsuario(Usuario usu) {
		Usuario usuTemp = usuarioRepo.findByUser(usu.getUser());

		if (usuTemp != null) {

			String passTemp = Auxiliar.hashear(usu.getPass() + usuTemp.getSalt());

			if (usuTemp.getPass().equalsIgnoreCase(passTemp)) {

				String token = jwtUtil.generateToken(usuTemp.getUser(), usuTemp.getRole());

				return token;
			}
			return null;
		}
		return null;
	}

}
