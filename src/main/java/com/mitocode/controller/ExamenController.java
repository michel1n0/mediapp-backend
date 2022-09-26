package com.mitocode.controller;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Examen;
import com.mitocode.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

    @Autowired
    private IExamenService service;

    @GetMapping
    public ResponseEntity<List<Examen>> listar() {
        List<Examen> resp = service.listar();
        return new ResponseEntity<List<Examen>>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> leerPorId(@PathVariable("id") Integer id) {
        var val = service.leerPorId(id);
        Examen resp = new Examen();

        if(val.isPresent()) {
            resp = val.get();
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Examen>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Examen obj){
        Examen resp = service.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.getIdExamen()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Examen obj){
        service.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        var val = service.leerPorId(id);
        Examen resp = new Examen();

        if(val.isPresent()) {
            service.eliminar(id);
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
