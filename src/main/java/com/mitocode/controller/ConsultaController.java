package com.mitocode.controller;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> resp = service.listar();
        return new ResponseEntity<List<Consulta>>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> leerPorId(@PathVariable("id") Integer id) {
        var val = service.leerPorId(id);
        Consulta resp = new Consulta();

        if(val.isPresent()) {
            resp = val.get();
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Consulta>(resp, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody ConsultaListaExamenDTO obj){
        Consulta resp = service.registrarTransaccional(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(resp.getIdConsulta()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Object> modificar(@Valid @RequestBody Consulta obj){
        service.modificar(obj);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        var val = service.leerPorId(id);
        Consulta resp = new Consulta();

        if(val.isPresent()) {
            service.eliminar(id);
        } else {
            throw new ModelNotFoundException("ID NO ENCONTRADO: " + id);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
