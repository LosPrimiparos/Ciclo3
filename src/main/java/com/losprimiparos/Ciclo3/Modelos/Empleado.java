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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "email_empleado", unique = true)
    private String emailEmpleado;

    @Column(name = "role_empleado")
    private String roleEmpleado;

    @Column(name = "estado_Vinculacion")
    private Boolean estadoEmpleado;

   @Column(name = "oathOId", unique = true)
    private String oathOId;

   @Column(name = "image")
   private  String image;


    /**public Empleado() {

    }

    public Empleado(String emailEmpleado, String image, String oathOId) {
        this.emailEmpleado = emailEmpleado;
        this.image = image;
        this.oathOId = oathOId;
    }*/

//@ManyToOne
    //@JoinColumn(name = "Empresa_id")
    //private Empresa empresa;

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
