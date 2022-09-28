package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Service.Implements.EmpleadoService;
import com.losprimiparos.Ciclo3.Modelos.Empleado;
import com.losprimiparos.Ciclo3.Service.Implements.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ControllerEmpleado {
    @Autowired
    EmpleadoService empleadoService;


    @GetMapping("/formUser")
    public String registrarEmpleado(Model model){
        model.addAttribute("empleado", new Empleado());
        return "formularioEmpleado";
    }

    @PostMapping("/saveUser")
    public String guardarEmpleado(Empleado empleado){
        empleadoService.guradarOrActualizarEmpleado(empleado);
        if(empleadoService.guradarOrActualizarEmpleado(empleado)==true){
            return "redirect:/users";
        }
        return "redirect:/formUser";
    }

    @GetMapping("/users")
    public String todosLosEmpleadosPorEmpresa(Model model){
        List<Empleado> todosLosEmpleados = empleadoService.consultarEmpleados();
        model.addAttribute("todosLosEmpleados", todosLosEmpleados);
        return "verEmpleados";
    }

    @GetMapping("/user/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id){
        Optional<Empleado> empleadoActualizar = empleadoService.buscarIdEmpleado(id);
        //Empresa emp = new Empresa();
        model.addAttribute("empleado",empleadoActualizar);
        return "editarEmpleado";
    }




    //Metodos para el @RETCONTROLLER
    /**
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
    }*/

}
