package com.ipartek.modelo;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("marca") 
    private List<Coche> coches;

    // Constructor vacío 
    public Marca() {
    }

    // Constructor con parámetros 
    public Marca(String nombre) {
        this.nombre = nombre;
    }

    // --- Getters y Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }
}