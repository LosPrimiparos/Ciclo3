package com.losprimiparos.Ciclo3.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpresa;
    private String nombreEmpresa;
    private String nitEmpresa;
    private String direccionEmpresa;
    private String emailEmpresa;
    private String telefonoEmpresa;
    private LocalDateTime creacionEmpresa;
    private LocalDateTime actualizacionEmpresa;
    private ArrayList<Empleado> empleadoList;
    private ArrayList <MovimientoDinero> movimientoDineroList;
}
