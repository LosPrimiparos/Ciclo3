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
@Table(name = "Empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpleado;
    private String nombresEmpleado;
    private String imagenEmpleado;
    private String emailEmpleado;
    private String numTelEmpleado;
    @ManyToOne
    @JoinColumn(name = "empresa_idempresa")
    private Empresa empresa;
    private String userEmpleado;
    private String roleEmpleado;
    private LocalDateTime creacionEmpleado;
    private LocalDateTime actualizacionEmpleado;
    private ArrayList<MovimientoDinero> movimientoDineroEmpleadoList;

    public Empleado(String nombresEmpleado, String imagenEmpleado, String emailEmpleado, String numTelEmpleado, Empresa empresa, String roleEmpleado) {
        this.nombresEmpleado = nombresEmpleado;
        this.imagenEmpleado = imagenEmpleado;
        this.emailEmpleado = emailEmpleado;
        this.numTelEmpleado = numTelEmpleado;
        this.empresa = empresa;
        this.roleEmpleado = roleEmpleado;
    }

    public Empresa getEmpresaEmpleado() {
        return empresa;
    }

    public void setEmpresaEmpleado(Empresa empresaEmpleado) {
        this.empresa = empresaEmpleado;
    }

}
