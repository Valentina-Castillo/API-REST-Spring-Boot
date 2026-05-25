package com.ipartek.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "coches")
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelo;

    private String matricula;

    private Double precio;

    private Integer potencia;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    @JsonIgnoreProperties("coches")
    private Marca marca;

    public Coche() {}
    
    public Coche(String modelo, String matricula, Integer potencia, Double precio, Marca marca) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.potencia = potencia;
        this.precio = precio;
        this.marca = marca;
    }

    public Integer getId()               { return id; }
    public void setId(Integer id)        { this.id = id; }

    public String getModelo()            { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMatricula()                 { return matricula; }
    public void setMatricula(String matricula)   { this.matricula = matricula; }

    public Double getPrecio()            { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getPotencia()               { return potencia; }
    public void setPotencia(Integer potencia)  { this.potencia = potencia; }

    public Marca getMarca()              { return marca; }
    public void setMarca(Marca marca)    { this.marca = marca; }
}
