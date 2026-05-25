package com.ipartek.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ipartek.modelo.Marca;
import com.ipartek.servicios.MarcaServicio;

@RestController
@RequestMapping("/api/v1/marcas")
@CrossOrigin(origins = "*")
public class MarcaAPI {

    @Autowired
    private MarcaServicio marcaServicio;

    // GET: /api/v1/marcas/ -> Listar todas
    @GetMapping("/")
    public ResponseEntity<List<Marca>> listarTodas() {
        List<Marca> marcas = marcaServicio.listarTodas();
        if (marcas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    // GET: /api/v1/marcas/{id} -> Obtener marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Integer id) {
        Marca marca = marcaServicio.buscarPorId(id);
        if (marca == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    // POST: /api/v1/marcas/ -> Guarda marca nueva
    @PostMapping("/")
    public ResponseEntity<Marca> guardar(@RequestBody Marca marca) {
        Marca nuevaMarca = marcaServicio.guardar(marca);
        return new ResponseEntity<>(nuevaMarca, HttpStatus.CREATED);
    }

    // PUT: /api/v1/marcas/ -> Modifica marca existente
    @PutMapping("/")
    public ResponseEntity<Marca> modificar(@RequestBody Marca marca) {
        if (marca.getId() == null || marcaServicio.buscarPorId(marca.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Marca marcaModificada = marcaServicio.modificar(marca);
        return new ResponseEntity<>(marcaModificada, HttpStatus.OK);
    }

    // DELETE: /api/v1/marcas/{id} -> Borrar marcas por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        boolean eliminado = marcaServicio.eliminar(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}