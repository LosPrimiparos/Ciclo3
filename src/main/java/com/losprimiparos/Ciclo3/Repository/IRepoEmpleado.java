package com.losprimiparos.Ciclo3.Repository;

import com.losprimiparos.Ciclo3.Modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepoEmpleado extends JpaRepository <Empleado, Long> {
    //List<Empleado> findbyEmpresa(Long id);
}
