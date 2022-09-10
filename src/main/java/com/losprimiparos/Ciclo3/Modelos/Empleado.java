package com.losprimiparos.Ciclo3.Modelos;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmpleado;

    @Column(name = "nombre_empleado", length = 70, nullable = false)
    private String nombreEmpleado;

    @Column(name = "email_empleado", length = 70, nullable = false)
    private String emailEmpleado;

    @ManyToOne
    @JoinColumn(name = "Empresa_id")
    private Empresa empresa;

    @Column(name = "role_empleado", length = 15)
    private String roleEmpleado;


    //@OneToMany
    //private List<MovimientoDinero> transaccionesEmpleadoList;

    //@Column(name = "usuario", length = 70)
    //private String userEmpleado;

    //@Column(name = "telefono_empleado", length = 12, nullable = false)
    //private String numTelEmpleado;

    //private String imagenEmpleado;
    //private LocalDateTime creacionEmpleado;
    //private LocalDateTime actualizacionEmpleado;


}
