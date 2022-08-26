package com.mitocode.controller;

import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public List<Paciente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Paciente leerPorId(@PathVariable("id") Integer id) {
        var val = service.leerPorId(id);

        if(val.isPresent()) {
            return val.get();
        }
        return null;
    }

    @PostMapping
    public void registrar(@RequestBody Paciente pac){
        service.registrar(pac);
    }

    @PutMapping
    public void modificar(@RequestBody Paciente pac){
        service.modificar(pac);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){
        service.eliminar(id);
    }
}
