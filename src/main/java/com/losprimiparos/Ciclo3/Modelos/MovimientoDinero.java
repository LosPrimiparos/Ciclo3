package com.losprimiparos.Ciclo3.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacciones")
public class MovimientoDinero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTransaccion;
    private String tipoTransaccion;
    private double montoTransaccion;
    private String conceptoTransaccion;
    private LocalDateTime creacionTransaccion;

    @ManyToOne
    @JoinColumn(name = "empresa_idtransaccion")
    private Empresa empresaUsuario;

    @ManyToOne
    @JoinColumn (name = "empleado_idtransaccion")
    private Empleado usuario;
    private LocalDateTime actulizacionTransaccion;

    public MovimientoDinero(String tipoTransaccion, double montoTransaccion, String conceptoTransaccion, Empresa empresaUsuario, Empleado usuario) {
        this.tipoTransaccion = tipoTransaccion;
        this.montoTransaccion = montoTransaccion;
        this.conceptoTransaccion = conceptoTransaccion;
        this.empresaUsuario = empresaUsuario;
        this.usuario = usuario;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getConceptoTransaccion() {
        return conceptoTransaccion;
    }

    public void setConceptoTransaccion(String conceptoTransaccion) {
        this.conceptoTransaccion = conceptoTransaccion;
    }

    public LocalDateTime getCreacionTransaccion() {
        return creacionTransaccion;
    }

    public void setCreacionTransaccion(LocalDateTime creacionTransaccion) {
        this.creacionTransaccion = creacionTransaccion;
    }

    public Empresa getEmpresaUsuario() {
        return empresaUsuario;
    }

    public void setEmpresaUsuario(Empresa empresaUsuario) {
        this.empresaUsuario = empresaUsuario;
    }

    public Empleado getUsuario() {
        return usuario;
    }

    public void setUsuario(Empleado usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getActulizacionTransaccion() {
        return actulizacionTransaccion;
    }

    public void setActulizacionTransaccion(LocalDateTime actulizacionTransaccion) {
        this.actulizacionTransaccion = actulizacionTransaccion;
    }
}
