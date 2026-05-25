package com.ipartek.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioAPI {

    @Autowired
    private UsuarioServicio usuarioServ; 

    @jakarta.annotation.PostConstruct
    public void init() {
        if (usuarioServ.obtenerTodosUsuarios().isEmpty()) {
            Usuario u = new Usuario();
            u.setId(0); u.setUser("admin"); u.setPass("1234"); u.setRole("ADMIN");
            usuarioServ.guardarUsuario(u);
        }
    }

    @PostMapping("/ValidarUsuario")
    public ResponseEntity<?> validarUsuario(@RequestBody Usuario loginUser) {
        String token = usuarioServ.validarUsuario(loginUser);
        if (token != null) {
            Map<String, String> res = new HashMap<>();
            res.put("token", token);
            res.put("user", loginUser.getUser());
            return ResponseEntity.ok(res);
        }
        Map<String, String> err = new HashMap<>();
        err.put("error", "Credenciales incorrectas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}