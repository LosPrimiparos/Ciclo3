package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Repository.IRepoEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    IRepoEmpresa empresaRepository;

    public List<Empresa> consultarEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Empresa buscarEmpresaId(Long idEmpresa){
                return empresaRepository.findById(idEmpresa).get();
    }

    public boolean saveOrUpdateEmpresa (Empresa empresa){
        Empresa emp = empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getIdEmpresa())!=null){
            return true;
        }
        return false;
    }

    public boolean eliminarEmpresa(Long idEmpresa){
        boolean bandera=true;
        Empresa auxEmpresa=empresaRepository.findById(idEmpresa).orElse(null);
        empresaRepository.deleteById(idEmpresa);
        if(auxEmpresa!=null){
            bandera=false;
        }else{
            empresaRepository.deleteById(idEmpresa);
        }
        return bandera;
    }

}
