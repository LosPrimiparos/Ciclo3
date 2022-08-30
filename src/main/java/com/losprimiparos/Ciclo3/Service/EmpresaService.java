package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Repository.IRepoEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.event.IIOReadProgressListener;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    IRepoEmpresa empresaRepository;

    public List<Empresa> getAllEmpresa(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Empresa getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();
    }

    /**public boolean saveOrUpdateEmpresa(Empresa empresa)
        Empresa emp = new empresaRepository.save(empresa);
            if (empresaRepository.findById(emp.getId())!=null){
                return true;
    }*/
}
