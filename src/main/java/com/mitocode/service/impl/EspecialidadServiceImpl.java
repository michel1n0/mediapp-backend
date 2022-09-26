package com.mitocode.service.impl;

import com.mitocode.model.Especialidad;
import com.mitocode.repo.IEspecialidadRepo;
import com.mitocode.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    @Autowired
    private IEspecialidadRepo repo;

    @Override
    public Especialidad registrar(Especialidad obj) {
        return repo.save(obj);
    }

    @Override
    public Especialidad modificar(Especialidad obj) {
        return repo.save(obj);
    }

    @Override
    public List<Especialidad> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Especialidad> leerPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
