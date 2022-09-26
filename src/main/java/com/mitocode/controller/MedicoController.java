package com.mitocode.controller;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        List<Medico> resp = service.listar();
        return new ResponseEntity<List<Medico>>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> leerPorId(@PathVariable("id") Integer id) {
        var val = service.leerPorId(id);
        Medico resp = new Medico();

        if(val.isPresent()) {
            resp = val.get();
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Medico>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Medico obj){
        Medico resp = service.registrar(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.getIdMedico()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Medico obj){
        service.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        var val = service.leerPorId(id);
        Medico resp = new Medico();

        if(val.isPresent()) {
            service.eliminar(id);
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
