package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.Empleado;
import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Repository.IRepoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    IRepoEmpleado empleadoRepository;

    //Metodo para ver todos los empleados registrados
    public List<Empleado> verEmpleados(){
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //Metodo para guardar o actualizar registros en Empleados
    public Empleado saveOrUpdateEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    //Metodo para buscar empleados por ID
    public Optional<Empleado> buscarEmpleadoId(Long id){ //Existe optional y asi se podria usar
        return empleadoRepository.findById(id);
    }

    //Metodo para eliminar un registro de Empleado por Id
    public boolean deleteEmpleado(Long id){
        boolean bandera=true;
        Empleado auxEmpleado= empleadoRepository.findById(id).orElse(null);
        empleadoRepository.deleteById(id);
        if(auxEmpleado !=null){
            bandera=false;
        }else{
            empleadoRepository.deleteById(id);
        }
        return bandera;
    }

}
