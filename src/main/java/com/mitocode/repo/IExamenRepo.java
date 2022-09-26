package com.mitocode.repo;

import com.mitocode.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExamenRepo extends JpaRepository<Examen, Integer> {

}
