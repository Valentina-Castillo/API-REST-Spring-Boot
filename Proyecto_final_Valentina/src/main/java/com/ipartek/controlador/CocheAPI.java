package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ipartek.modelo.Coche;
import com.ipartek.servicios.CocheServicio;

@RestController
@RequestMapping("/api/v1/coches")
@CrossOrigin("*")
public class CocheAPI {

    @Autowired
    private CocheServicio cocheServicio;

    @GetMapping
    public ResponseEntity<List<Coche>> listarTodos() {
        List<Coche> coches = cocheServicio.listarTodos();
        if (coches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coches, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Coche> guardar(@RequestBody Coche coche) {
        Coche nuevoCoche = cocheServicio.guardar(coche);
        return new ResponseEntity<>(nuevoCoche, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(
            @PathVariable Integer id,
            @RequestBody Coche coche) {

        Coche cocheExistente = cocheServicio.buscarPorId(id);
        if (cocheExistente == null) {
            return ResponseEntity.notFound().build();
        }
        coche.setId(id);
        Coche cocheModificado = cocheServicio.modificar(coche);
        return ResponseEntity.ok(cocheModificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        boolean eliminado = cocheServicio.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ordenados")
    public List<Coche> listarOrdenadosPorPrecio() {
        return cocheServicio.listarOrdenadosPorPrecio();
    }
}
