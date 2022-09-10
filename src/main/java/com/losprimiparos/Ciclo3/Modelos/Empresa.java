package com.losprimiparos.Ciclo3.Modelos;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Empresa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEmpresa;

    @Column(name = "nombre_empresa", length = 70, nullable = false)
    private String nombreEmpresa;

    @Column(name = "nit_empresa", length = 13, nullable = false)
    private String nitEmpresa;

    @Column(name = "direccion_empresa", length = 70, nullable = false)
    private String direccionEmpresa;

    @Column(name = "email_empresa", length = 70, nullable = false)
    private String emailEmpresa;

    @Column(name = "telefono_empresa", length = 15)
    private String telefonoEmpresa;

    //@OneToMany
    //@Column(name = "id_transsaciones")
    //private List transacionesEmpresa;

    //@OneToMany
    //private List<Empleado> usuarioList;

    //@OneToMany
    //private List<MovimientoDinero> transaccionesList;

    /**@Column(name = "fecha_creacion_empresa", length = 10, nullable = false)
    private LocalDateTime creacionEmpresa;

    @Column(name = "fecha_actualizacion_empresa", length = 10, nullable = false)
    private LocalDateTime actualizacionEmpresa;*/

}
