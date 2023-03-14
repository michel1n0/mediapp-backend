package com.mitocode.service.impl;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Autowired
    private IConsultaExamenRepo ceRepo;

    @Transactional
    @Override
    public Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO) {
        consultaDTO.getConsulta().getDetalleConsulta().forEach( det -> det.setConsulta(consultaDTO.getConsulta()));
        repo.save(consultaDTO.getConsulta());

        consultaDTO.getListExamen().forEach(ex -> ceRepo.registrar(consultaDTO.getConsulta().getIdConsulta(), ex.getIdExamen()));
        return consultaDTO.getConsulta();
    }

    @Override
    public Consulta registrar(Consulta obj) {
        obj.getDetalleConsulta().forEach(det -> {
            det.setConsulta(obj);
        });
        return repo.save(obj);
    }

    @Override
    public Consulta modificar(Consulta obj) {
        return repo.save(obj);
    }

    @Override
    public List<Consulta> listar() {
        return repo.findAll();
    }

    @Override
    public Optional<Consulta> leerPorId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

}
