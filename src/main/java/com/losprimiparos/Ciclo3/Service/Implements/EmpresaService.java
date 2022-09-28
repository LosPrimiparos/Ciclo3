package com.losprimiparos.Ciclo3.Service.Implements;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Repository.IRepoEmpresa;
import com.losprimiparos.Ciclo3.Service.IempresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService implements IempresaService {

    @Autowired
    IRepoEmpresa empresaRepository;

    @Override
    public boolean crearEmpresa(Empresa empresa) {
        boolean registrar = false;
        if (empresa.getIdEmpresa() > 0){
            empresa.setIdEmpresa(empresa.getIdEmpresa());
            empresa.setNombreEmpresa(empresa.getNombreEmpresa());
            empresa.setNitEmpresa(empresa.getNitEmpresa());
            empresa.setDireccionEmpresa(empresa.getDireccionEmpresa());
            empresa.setTelefonoEmpresa(empresa.getTelefonoEmpresa());
            empresa.setEmailEmpresa(empresa.getEmailEmpresa());
            empresa.setTipoEmpresa(empresa.getTipoEmpresa());
            empresaRepository.save(empresa);
            registrar = true;
        } else if (empresa != null) {
            empresaRepository.save(empresa);
            registrar = true;
        }

        return registrar;
    }

    @Override
    public List<Empresa> consultarEmpresas() {
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    @Override
    public Optional<Empresa> buscarEmpresaId(Integer idEmpresa) {
        return Optional.of(empresaRepository.findById(idEmpresa).get());
    }

    @Override
    public Void eliminarEmpresa(Integer idEmpresa){
        empresaRepository.deleteById(idEmpresa);
        return null;
    }


    //Metodos de servicio para el @RESTCONTROLLER
    /**public List<Empresa> consultarEmpresas(){
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
    }*/

}
