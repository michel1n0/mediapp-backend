package com.mitocode.repo;

import com.mitocode.model.Consulta;
import com.mitocode.model.ConsultaExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IConsultaExamenRepo extends JpaRepository<ConsultaExamen, Integer> {

    //@Transactional
    @Modifying
    @Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
    Integer registrar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen);
}
