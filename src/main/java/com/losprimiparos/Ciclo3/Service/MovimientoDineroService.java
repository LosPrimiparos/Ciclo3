package com.losprimiparos.Ciclo3.Service;

import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;
import com.losprimiparos.Ciclo3.Repository.IRepoEmpresa;
import com.losprimiparos.Ciclo3.Repository.IRepoMovimientoDinero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoDineroService {

    @Autowired
    IRepoMovimientoDinero movimientosRepository;

    @Autowired
    EmpresaService empresaService;

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
    }

}
