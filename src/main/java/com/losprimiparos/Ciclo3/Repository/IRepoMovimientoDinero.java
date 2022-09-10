package com.losprimiparos.Ciclo3.Repository;

import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IRepoMovimientoDinero extends JpaRepository <MovimientoDinero, Long> {

    //Metodo para filtrar movimientos por empresa
    @Query(value="select * from transacciones where Empresa_id = ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Long idEmpresa);

    //Metodo para eliminar movimientos por empresa

}
