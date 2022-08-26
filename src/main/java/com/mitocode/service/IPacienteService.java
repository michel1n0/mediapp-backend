package com.mitocode.service;

import com.mitocode.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    void registrar(Paciente pac);
    void modificar(Paciente pac);
    List<Paciente> listar();
    Optional<Paciente> leerPorId(Integer id);
    void eliminar(Integer id);
}
