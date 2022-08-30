package com.losprimiparos.Ciclo3.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private int idEmpleado;
    private String nombresEmpleado;
    private String imagenEmpleado;
    private String emailEmpleado;
    private String numTelEmpleado;
    private String empresaPertenece;
    private String userEmpleado;
    private String roleEmpleado;
    private LocalDateTime creacionEmpleado;
    private LocalDateTime actualizacionEmpleado;
    private ArrayList<MovimientoDinero> movimientoDineroEmpleadoList;

}
