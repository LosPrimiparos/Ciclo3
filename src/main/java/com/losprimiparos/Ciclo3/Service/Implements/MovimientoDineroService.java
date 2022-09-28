package com.losprimiparos.Ciclo3.Service.Implements;

import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;
import com.losprimiparos.Ciclo3.Repository.IRepoMovimientoDinero;
import com.losprimiparos.Ciclo3.Service.ImovimientoService;
import com.losprimiparos.Ciclo3.Service.Implements.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDineroService implements ImovimientoService {

    @Autowired
    IRepoMovimientoDinero movimientosRepository;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpleadoService empleadoService;

    @Override
    public List<MovimientoDinero> consultartransacciones() {
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientosRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return movimientosList;
    }

    @Override
    public MovimientoDinero transaccionPorId(Integer idTransaccion) {
        return movimientosRepository.findById(idTransaccion).get();
    }

    @Override
    public boolean guardarOrActualizarTransacciones(MovimientoDinero movimientoDinero) {
        MovimientoDinero mov=movimientosRepository.save(movimientoDinero);
        if (movimientosRepository.findById(mov.getIdTransaccion())!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarTransaccion(Integer idTransaccion) {
        movimientosRepository.deleteById(idTransaccion); //Eliminar usando el metodo que nos ofrece el repositorio
        if(this.movimientosRepository.findById(idTransaccion).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    @Override
    public ArrayList<MovimientoDinero> transaccionesPorEmpleado(Integer idEmpleado) {
        return movimientosRepository.findByEmpleado(idEmpleado);
    }

    @Override
    public ArrayList<MovimientoDinero> transaccionesPorEmpresa(Integer idEmpresa) {
        return movimientosRepository.findByEmpresa(idEmpresa);
    }

    @Override
    public Long obtenerSumaMontos() {
        return movimientosRepository.SumarMonto();
    }

    @Override
    public Long obtenerSumaTipoTransaccion(String tipoTransaccion) {
        return movimientosRepository.MontosPorTipoTransaccion(tipoTransaccion);
    }

    @Override
    public Long MontosPorEmpleado(Integer idEmpleado) {
        return movimientosRepository.MontosPorEmpleado(idEmpleado);
    }

    @Override
    public Long MontosPorEmpresa(Integer idEmpresa) {
        return movimientosRepository.MontosPorEmpresa(idEmpresa);
    }

    @Override
    public Integer IdPorCorreo(String Correo) {
        return movimientosRepository.IdPorCorreo(Correo);
    }


    //Metodos para el @RESTCONTROLLER
    /**
    //Identificar la existencia de la empresa y la extraccion del IdEmpresa
    public boolean llaveEmpresa (Long idEmpresa){
        Long idVerificador = empresaService.buscarEmpresaId(idEmpresa).getIdEmpresa();
        if(idVerificador!= null){
            return true;
        }
        return false;
    }

    public MovimientoDinero capturarTransaccion(MovimientoDinero movimientoDinero){
        MovimientoDinero transaccion = movimientosRepository.save(movimientoDinero);
        return transaccion;
    }


    //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Long idEmpresa) {
        return movimientosRepository.findByEmpresa(idEmpresa);
    }

    public List<MovimientoDinero> getAllMovimientos() {
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        movimientosRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;

    }

    //Eliminar movimiento por id
    public boolean deleteMovimiento(Long idTransaccion){
        //Eliminar usando el metodo que nos ofrece el repositorio
        movimientosRepository.deleteById(idTransaccion);
        //Si al buscar el movimiento lo encontramos, no se eliminó (false)
        if(this.movimientosRepository.findById(idTransaccion).isPresent()){
            return false;
        }
        //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
        return true;
    }*/

}
