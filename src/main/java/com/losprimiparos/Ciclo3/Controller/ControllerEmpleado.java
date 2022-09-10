package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Service.EmpleadoService;
import com.losprimiparos.Ciclo3.Modelos.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControllerEmpleado {
    @Autowired
    EmpleadoService empleadoService;

    //Ver json de todas los empleados
    @GetMapping("/users")
    public List<Empleado> verEmpleados(){
        return empleadoService.verEmpleados();
    }

    //Guardar un empleado nuevo
    @PostMapping("/users")
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado empl){
        return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empl));
    }

    //Consultar empleado por ID
    @GetMapping("users/{id}")
    public Optional<Empleado> empleadoPorID(@PathVariable("id") Long idEmpleado){
        return this.empleadoService.buscarEmpleadoId(idEmpleado);
    }

    @PatchMapping("/users/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Long idEmpleado, @RequestBody Empleado empleado){
        Empleado empl=empleadoService.buscarEmpleadoId(idEmpleado).get();
        empl.setNombreEmpleado(empleado.getNombreEmpleado());
        empl.setEmailEmpleado(empleado.getEmailEmpleado());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRoleEmpleado(empleado.getRoleEmpleado());
        return empleadoService.saveOrUpdateEmpleado(empl);
    }

    //Metodo para eliminar empleados por id
    @DeleteMapping("/users/{id}")
    public String DeleteEmpleado(@PathVariable("id") Long idEmpleado) {
        boolean respuesta = empleadoService.deleteEmpleado(idEmpleado); //eliminamos usando el servicio de nuestro service
        if (respuesta) { //si la respuesta booleana es true, si se eliminò
            return "No se puedo eliminar correctamente el empleado con id = " + idEmpleado;
        }//Si la respuesta booleana es false, no se eliminó
        return "Se pudo eliminar correctamente el empleado con id = " + idEmpleado;
    }

}
