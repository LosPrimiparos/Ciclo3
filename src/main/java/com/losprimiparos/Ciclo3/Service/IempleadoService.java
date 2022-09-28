package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.Empleado;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IempleadoService {

    public List<Empleado> consultarEmpleados();

    public Optional<Empleado> buscarIdEmpleado(Integer idEmpleado);

    public boolean guradarOrActualizarEmpleado (Empleado empleado);

    public boolean desvincularEmpleado(Integer idEmpleado);

    public Empleado capturarOrCreacionUsuario(Map<String, Object> UserData);

    public Empleado crearUser (Empleado newUser);

    public Empleado buscarPorEmailUser (String emailEmpleado);
}
