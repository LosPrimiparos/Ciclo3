package com.losprimiparos.Ciclo3.Repository;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoEmpresa extends JpaRepository <Empresa, Integer>{

}
