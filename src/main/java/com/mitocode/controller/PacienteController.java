package com.mitocode.controller;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        List<Paciente> resp = service.listar();
        return new ResponseEntity<List<Paciente>>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> leerPorId(@PathVariable("id") Integer id) {
        var val = service.leerPorId(id);
        Paciente resp = new Paciente();

        if(val.isPresent()) {
            resp = val.get();
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Paciente>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente obj){
        Paciente resp = service.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.getIdPaciente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Paciente obj){
        service.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        var val = service.leerPorId(id);
        Paciente resp = new Paciente();

        if(val.isPresent()) {
            service.eliminar(id);
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
