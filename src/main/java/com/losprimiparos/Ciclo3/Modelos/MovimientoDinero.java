package com.losprimiparos.Ciclo3.Modelos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transacciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovimientoDinero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTransaccion;

    @Column(name = "tipo_transaccion", length = 20, nullable = false)
    private String tipoTransaccion;

    @Column(name = "concepto_transaccion", length = 70, nullable = false)
    private String conceptoTransaccion;

    @ManyToOne
    @JoinColumn(name = "Empresa_id")
    private Empresa empresa;

    @Column(name = "monto_transaccion", length = 10, nullable = false)
    private double montoTransaccion;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado usuario;

}
