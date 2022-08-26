package com.mitocode.repo;

import com.mitocode.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepo extends JpaRepository<Paciente, Integer> {

}
