package com.mitocode.controller;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Especialidad;
import com.mitocode.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService service;

    @GetMapping
    public ResponseEntity<List<Especialidad>> listar() {
        List<Especialidad> resp = service.listar();
        return new ResponseEntity<List<Especialidad>>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> leerPorId(@PathVariable("id") Integer id) {
        var val = service.leerPorId(id);
        Especialidad resp = new Especialidad();

        if(val.isPresent()) {
            resp = val.get();
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Especialidad>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Especialidad obj){
        Especialidad resp = service.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.getIdEspecialidad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Especialidad obj){
        service.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        var val = service.leerPorId(id);
        Especialidad resp = new Especialidad();

        if(val.isPresent()) {
            service.eliminar(id);
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
