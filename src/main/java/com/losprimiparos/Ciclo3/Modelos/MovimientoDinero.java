package com.losprimiparos.Ciclo3.Modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDinero {

    private int idTransaccion;
    private String tipoTransaccion;
    private double montoTransaccion;
    private String conceptoTransaccion;
    private LocalDateTime creacionTransaccion;
    private String empresaTransaccion;
    private String usuarioTransaccion;
    private LocalDateTime actulizacionTransaccion;

}
