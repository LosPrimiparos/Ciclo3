package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerEmpresa {

    @Autowired
    EmpresaService empresaService;

    @GetMapping({"/enterprises"})
    public List<Empresa> verEmpresas() {
        return empresaService.consultarEmpresas();
    }

    //Guardar el json del body como una nueva empresa o registro en nuestra bd
    @PostMapping("/enterprises")
    public boolean guardarEmpresa(@RequestBody Empresa empresa) {
        return this.empresaService.saveOrUpdateEmpresa(empresa);
    }

    @GetMapping("/enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Long idEmpresa) {
        return this.empresaService.buscarEmpresaId(idEmpresa);
    }

    @PatchMapping("/enterprises/{id}")
    public boolean actualizarEmpresa(@PathVariable("id") Long idEmpresa, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.buscarEmpresaId(idEmpresa);
        emp.setNombreEmpresa(empresa.getNombreEmpresa());
        emp.setNitEmpresa(empresa.getNitEmpresa());
        emp.setDireccionEmpresa(empresa.getDireccionEmpresa());
        emp.setTelefonoEmpresa(empresa.getTelefonoEmpresa());
        return  empresaService.saveOrUpdateEmpresa(emp);

    }
    //Eliminar registro de la bd
    @DeleteMapping ("enterprises/{id}")
    public String EliminarEmpresa(@PathVariable("id") Long idEmpresa){
        boolean respuesta= this.empresaService.eliminarEmpresa(idEmpresa);
        if (respuesta){  //Si respuesta es true?
            return "No se pudo eliminar la empresa con id = " +idEmpresa;
        }
        else{
            return "Se elimino la empresa con id = "+idEmpresa;
        }
    }
}