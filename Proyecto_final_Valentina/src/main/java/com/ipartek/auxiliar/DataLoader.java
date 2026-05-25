package com.ipartek.auxiliar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.ipartek.modelo.Coche;
import com.ipartek.modelo.Marca;
import com.ipartek.repositorio.CocheRepositorio;
import com.ipartek.repositorio.MarcaRepositorio;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CocheRepositorio cocheRepo;

    @Autowired
    private MarcaRepositorio marcaRepo;

    @Override
    public void run(String... args) throws Exception {

        if (marcaRepo.count() > 0 || cocheRepo.count() > 0) {
            System.out.println("BD ya inicializada. Saltando DataLoader.");
            return;
        }

        Marca toyota = marcaRepo.save(new Marca("Toyota"));
        Marca bmw    = marcaRepo.save(new Marca("BMW"));
        Marca ford   = marcaRepo.save(new Marca("Ford"));

        cocheRepo.save(new Coche("Corolla", "1234ABC", 140, 25000.0, toyota));
        cocheRepo.save(new Coche("Yaris",   "5678DEF", 116, 20000.0, toyota));
        cocheRepo.save(new Coche("Serie 3", "9012GHI", 184, 45000.0, bmw));
        cocheRepo.save(new Coche("X5",      "3456JKL", 286, 75000.0, bmw));
        cocheRepo.save(new Coche("Focus",   "7890MNP", 125, 22000.0, ford));

        System.out.println("Datos iniciales cargados correctamente.");
    }
}