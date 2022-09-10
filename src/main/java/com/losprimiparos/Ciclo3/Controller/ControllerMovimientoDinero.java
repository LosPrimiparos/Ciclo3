package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Service.EmpresaService;
import com.losprimiparos.Ciclo3.Service.MovimientoDineroService;
import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;
import com.sun.xml.bind.v2.model.annotation.LocatableAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerMovimientoDinero {

    @Autowired
    MovimientoDineroService movimientosService;

    @Autowired
    EmpresaService empresaService;

    @PostMapping(path = "/enterprises/{id}/movements")
    public String crearNuevoMoviemtoEmpresa (@PathVariable("id") Long idEmpresa, @RequestBody MovimientoDinero movimiento){
        boolean existeEmpresa = movimientosService.llaveEmpresa(idEmpresa);
        if((existeEmpresa == true) && (idEmpresa == movimiento.getEmpresa().getIdEmpresa())){
            MovimientoDinero transaccionTemp = new MovimientoDinero();
            transaccionTemp = movimientosService.capturarTransaccion(movimiento);
            Empresa emp = empresaService.buscarEmpresaId(idEmpresa);
            String nombreEmp = emp.getNombreEmpresa();
            return "La transanccion se registro con exito a la empresa: " +nombreEmp;
        } else if ((existeEmpresa == true) && (idEmpresa != movimiento.getEmpresa().getIdEmpresa())) {
            return "La transanccion NO se puede registrar, el idEmpresa de la transaccion suministrado NO corresponde a la empresa Seleccionado";
        }
        return "La Empresa Seleccionada no se encuentra registrada en la base de datos de la plataforma";
    }

    //Editar o actualizar un movimiento
    @PatchMapping("/enterprises/{id}/movements")
    public String actualizarMovimiento(@PathVariable("id") Long idEmpresa, @RequestBody MovimientoDinero movimiento) {
        boolean existeEmpresa = movimientosService.llaveEmpresa(idEmpresa);
        if((existeEmpresa == true) && (idEmpresa == movimiento.getEmpresa().getIdEmpresa())){
            MovimientoDinero transaccionTemp = new MovimientoDinero();
            transaccionTemp.setTipoTransaccion(movimiento.getTipoTransaccion());
            transaccionTemp.setMontoTransaccion(movimiento.getMontoTransaccion());
            transaccionTemp.setConceptoTransaccion (movimiento.getConceptoTransaccion());
            transaccionTemp = movimientosService.capturarTransaccion(movimiento);
            Empresa emp = empresaService.buscarEmpresaId(idEmpresa);
            String nombreEmp = emp.getNombreEmpresa();
            return "La transanccion se actualizo con exito a la empresa: " +nombreEmp;
        } else if ((existeEmpresa == true) && (idEmpresa != movimiento.getEmpresa().getIdEmpresa())) {
            return "La transanccion NO se puede actualizar, el idEmpresa de la transaccion suministrado NO corresponde a la empresa Seleccionado";
        }
        return "La Empresa Seleccionada no se encuentra registrada en la base de datos de la plataforma";
    }

    //Consultar todos los movimientos
    @GetMapping(path = "/enterprises/{id}/movements")
    public List<MovimientoDinero> conultarMovimientosPorEmpresa(@PathVariable("id") Long idEmpresa) {
        return movimientosService.obtenerPorEmpresa(idEmpresa);
    }

    @DeleteMapping(path = "/enterprises/{id}/movements")
    public String deleteMovimiento(@PathVariable("id") Long idEmpresa, @RequestBody MovimientoDinero movimiento) {
        boolean existeEmpresa = movimientosService.llaveEmpresa(idEmpresa);
        if ((existeEmpresa == true) && (idEmpresa == movimiento.getEmpresa().getIdEmpresa())) {
            Long idTranTemp = movimiento.getIdTransaccion();
            boolean respuesta = movimientosService.deleteMovimiento(idTranTemp);
            if (respuesta) {
                return "Se elimino correctamente el movimiento con id " + idEmpresa;            }
            return "No se pudo eliminar el movimiento con id " + idEmpresa;
        }
        return "La Empresa Seleccionada no se encuentra registrada en la base de datos de la plataforma";
    }

    @GetMapping(path = "/enterprises/movements")
    public List<MovimientoDinero> verMovimientos() {
        return movimientosService.getAllMovimientos();
    }

}
