package com.losprimiparos.Ciclo3.Modelos;

import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "transacciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovimientoDinero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaccion;

    @ManyToOne
    @JoinColumn(name = "Empresa_id")
    private Empresa empresa;

    @Column(name = "tipo_transaccion", length = 20, nullable = false)
    private String tipoTransaccion;

    @Column(name = "monto_transaccion", length = 10, nullable = false)
    private double montoTransaccion;

    @Column(name = "concepto_transaccion", length = 70, nullable = false)
    private String conceptoTransaccion;



   //@ManyToOne
   // @JoinColumn(name = "Empleado_id")
   // private Empleado usuario;

    //@Column(name = "fecha_creacion")
    //private LocalDateTime creacionTransaccion;

}
