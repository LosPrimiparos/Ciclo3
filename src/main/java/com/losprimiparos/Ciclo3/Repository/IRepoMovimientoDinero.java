package com.losprimiparos.Ciclo3.Repository;

import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IRepoMovimientoDinero extends JpaRepository <MovimientoDinero, Integer> {

    //Metodo para filtrar movimientos por empresa
    @Query(value="select * from transacciones where Empresa_id = ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer idEmpresa);

    /**
    //Metodo para filtrar movimientos por empresa
    @Query(value="select * from movimientos where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);*/

    //Metodo para filtrar movimientos por empleado
    @Query(value ="select * from transacciones where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpleado(Integer idEmpleado);

    //Metodo para ver la suma de TODOS LOS MOVIMIENTOS
    @Query(value="SELECT SUM(monto_transaccion) from transacciones", nativeQuery = true)
    public abstract Long SumarMonto();

    //Metodo para ver la suma de los montos por empleado
    @Query(value="SELECT SUM(monto_transaccion) from transacciones where empleado_id=?1", nativeQuery = true)
    public abstract Long MontosPorEmpleado(Integer idEmpleado); //id del empleado

    //Metodo para ver la suma de los movimientos por empresa
    @Query(value="select sum(monto_transaccion) from transacciones where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    public abstract Long MontosPorEmpresa(Integer idEmpresa); //Id de la empresa

    //Metodo para ver la suma de los montos por tipo de transaccion
    @Query(value="SELECT SUM(monto_transaccion) from transacciones where tipo_transaccion=?1", nativeQuery = true)
    public abstract Long MontosPorTipoTransaccion(String tipoTransaccion); //tipo de transaccion (Egreso o Ingreso)

    //Metodo que me trae el id de un usuario cuando tengo su correo
    @Query(value="select id from empleado where correo=?1", nativeQuery = true)
    public abstract Integer IdPorCorreo(String correo);


}
