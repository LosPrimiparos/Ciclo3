package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;

import java.util.ArrayList;
import java.util.List;

public interface ImovimientoService {

    public List<MovimientoDinero> consultartransacciones();

    public MovimientoDinero transaccionPorId(Integer idTransaccion);

    public boolean guardarOrActualizarTransacciones(MovimientoDinero movimientoDinero);

    public boolean eliminarTransaccion(Integer idTransaccion);

    public ArrayList<MovimientoDinero> transaccionesPorEmpleado(Integer idEmpleado);

    public ArrayList<MovimientoDinero> transaccionesPorEmpresa(Integer idEmpresa);

    public Long obtenerSumaMontos();

    public Long obtenerSumaTipoTransaccion(String tipoTransaccion);

    public Long MontosPorEmpleado(Integer idEmpleado);

    public Long MontosPorEmpresa(Integer idEmpresa);

    public Integer IdPorCorreo(String Correo);

}
