package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.Empresa;

import java.util.List;
import java.util.Optional;

public interface IempresaService {

    public List<Empresa> consultarEmpresas();

    public Optional<Empresa> buscarEmpresaId(Integer idEmpresa);

    public boolean crearEmpresa (Empresa empresa);

    public Void eliminarEmpresa(Integer idEmpresa);

}
