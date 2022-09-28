package com.losprimiparos.Ciclo3.Service.Implements;

import com.losprimiparos.Ciclo3.Modelos.Empleado;
import com.losprimiparos.Ciclo3.Repository.IRepoEmpleado;
import com.losprimiparos.Ciclo3.Service.IempleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpleadoService implements IempleadoService {

    @Autowired
    IRepoEmpleado empleadoRepository;

    @Override
    public boolean guradarOrActualizarEmpleado(Empleado empleado) {
        boolean registrar = false;
        if (empleado.getIdEmpleado() > 0){
            empleado.setIdEmpleado(empleado.getIdEmpleado());
            empleado.setNombreEmpleado(empleado.getNombreEmpleado());
            empleado.setEmailEmpleado(empleado.getEmailEmpleado());
            empleado.setRoleEmpleado(empleado.getRoleEmpleado());
            empleado.setEstadoEmpleado(empleado.getEstadoEmpleado());
            empleadoRepository.save(empleado);
            registrar = true;
        }
        if (empleado != null) {
            empleadoRepository.save(empleado);
            registrar = true;
        }

        return registrar;
    }

    @Override
    public List<Empleado> consultarEmpleados() {
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //Metodo para buscar empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id) {
        return empleadoRepository.findByEmpresa(id);
    }

    @Override
    public Optional<Empleado> buscarIdEmpleado(Integer idEmpleado) {
        return empleadoRepository.findById(idEmpleado);
    }


    @Override
    public boolean desvincularEmpleado(Integer idEmpleado) {
        boolean bandera=true;
        Empleado auxEmpleado= empleadoRepository.findById(idEmpleado).orElse(null);
        empleadoRepository.deleteById(idEmpleado);
        if(auxEmpleado !=null){
            bandera=false;
        }else{
            empleadoRepository.deleteById(idEmpleado);
        }
        return bandera;
    }

    @Override
    public Empleado crearUser(Empleado newUser) {
        return this.empleadoRepository.save(newUser);
    }

    @Override
    public Empleado buscarPorEmailUser(String emailEmpleado) {
        return this.empleadoRepository.findByEmail(emailEmpleado);

    }

    @Override
    public Empleado capturarOrCreacionUsuario(Map<String, Object> userData) {
        String email = (String) userData.get("email");

        Empleado userEmail = buscarPorEmailUser(email);

        if ( userEmail == null){
            String name = (String) userData.get("name");
            String image = (String) userData.get("picture");
            String authOId = (String) userData.get("sub");

            Empleado newUser = new Empleado();

            return crearUser(newUser);
        }

        return userEmail;

    }




    /**
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
    }*/

}
