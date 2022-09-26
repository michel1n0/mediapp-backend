package com.mitocode.service.impl;

import com.mitocode.model.Medico;
import com.mitocode.repo.IMedicoRepo;
import com.mitocode.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl implements IMedicoService {

    @Autowired
    private IMedicoRepo repo;

    @Override
    public Medico registrar(Medico obj) {
        return repo.save(obj);
    }

    @Override
    public Medico modificar(Medico obj) {
        return repo.save(obj);
    }

    @Override
    public List<Medico> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Medico> leerPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
