package com.losprimiparos.Ciclo3.Repository;

import com.losprimiparos.Ciclo3.Modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IRepoEmpleado extends JpaRepository <Empleado, Integer> {
    @Query(value="SELECT * FROM empleado where empresa_id= ?1", nativeQuery=true)
    public abstract ArrayList<Empleado> findByEmpresa(Integer id);

    @Query(value="SELECT * FROM empleado where email_empleado= ?1", nativeQuery=true)
    public abstract Empleado findByEmail(String emailEmpleado);


}
