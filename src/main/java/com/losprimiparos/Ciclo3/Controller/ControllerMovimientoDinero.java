package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empleado;
import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Modelos.MovimientoDinero;
import com.losprimiparos.Ciclo3.Repository.IRepoMovimientoDinero;
import com.losprimiparos.Ciclo3.Service.Implements.EmpleadoService;
import com.losprimiparos.Ciclo3.Service.Implements.EmpresaService;
import com.losprimiparos.Ciclo3.Service.Implements.MovimientoDineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class ControllerMovimientoDinero {

    @Autowired
    MovimientoDineroService movimientosService;

    @Autowired
    IRepoMovimientoDinero movimientosRepository;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    EmpleadoService empleadoService;



    @GetMapping("/movements")
    public String visualizarTransancciones (Model model){
        List<MovimientoDinero> listTransacciones = movimientosService.consultartransacciones();
        model.addAttribute("listTransacciones", listTransacciones);
        Long sumaMonto = movimientosService.obtenerSumaMontos();
        model.addAttribute("SumaMontos", sumaMonto);
        Long sumaEgresos = movimientosService.obtenerSumaTipoTransaccion("Egreso");
        model.addAttribute("SumaEgresos", sumaEgresos);
        Long sumaIngresos = movimientosService.obtenerSumaTipoTransaccion("Ingreso");
        model.addAttribute("SumaIngresos", sumaIngresos);
        return "verTransacciones";
    }

    @GetMapping("/formMovement")
    //Controlador que nos lleva al template donde podremos crear un nuevo movimiento
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero movimientoDinero = new MovimientoDinero();
        model.addAttribute("movimientoDinero", movimientoDinero);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = empleadoService.consultarEmpleados();
        model.addAttribute("listaEmpleados", listaEmpleados);
        List<Empresa> listaEmpresas = empresaService.consultarEmpresas();
        model.addAttribute("listaEmpresas", listaEmpresas);
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String correo = auth.getName();
        //Integer idEmpleado = serviceMovimientos.IdPorCorreo(correo);
        //model.addAttribute("idEmpleado", idEmpleado);
        return "formularioTransaccion";
    }

    @PostMapping("/saveMovement")
    public String guardarMovimiento(MovimientoDinero movimientoDinero, RedirectAttributes redirectAttributes) {
        movimientosService.guardarOrActualizarTransacciones(movimientoDinero);
        if (movimientosService.guardarOrActualizarTransacciones(movimientoDinero)==true) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/movements";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/formMovement";

    }

    @GetMapping("/editMovement/{id}")
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero movimientoDinero = movimientosService.transaccionPorId(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("movimientoDinero", movimientoDinero);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = empleadoService.consultarEmpleados();
        model.addAttribute("listaEmpleados", listaEmpleados);
        List<Empresa> listaEmpresas = empresaService.consultarEmpresas();
        model.addAttribute("listaEmpresas", listaEmpresas);
        return "editarTransaccion";
    }



    @GetMapping("/deleteMovement/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (movimientosService.eliminarTransaccion(id)==true) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/movements";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/movements";
    }

    @GetMapping("/user/{id}/movements") //Filtro de movimientos por empleados
    public String movimientosPorEmpleado(@PathVariable("id") Integer id, Model model) {
        List<MovimientoDinero> listTransacciones = movimientosService.transaccionesPorEmpleado(id);
        model.addAttribute("listTransacciones", listTransacciones);
        Long sumaMonto = movimientosService.MontosPorEmpleado(id);
        model.addAttribute("SumaMontos", sumaMonto);

        return "verTransacciones"; //Llamamos al HTML
    }

    @GetMapping("/eterpris/{id}/movements") //Filtro de movimientos por empresa
    public String movimientosPorEmpresa(@PathVariable("id") Integer id, Model model) {
        List<MovimientoDinero> listTransacciones = movimientosService.transaccionesPorEmpresa(id);
        model.addAttribute("listTransacciones", listTransacciones);
        Long sumaMonto = movimientosService.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos", sumaMonto);
        return "verTransacciones"; //Llamamos al HTML
    }

    /**
    @RequestMapping("/movements")
    public String viewMovimiento(@RequestParam(value = "pagina", required = false, defaultValue = "0") int NumeroPagina,
                                 @RequestParam(value = "medida", required = false, defaultValue = "5") int medida, // numero que muesta las listas
                                 Model model, @ModelAttribute("mensaje") String mensaje) {
        Page<MovimientoDinero> paginaMovimientos = movimientosRepository.findAll(PageRequest.of(NumeroPagina, medida));

        model.addAttribute("listTransacciones", paginaMovimientos.getContent());
        model.addAttribute("paginas", new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje", mensaje);
        Long sumaMonto = movimientosService.obtenerSumaMontos();
        model.addAttribute("SumaMontos", sumaMonto);//Mandamos la suma de todos los montos a la plantilla
        return "verTransacciones";
    }

    //Controlador que me lleva al template de No autorizado
    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){
        return "accesoDenegado";
    }

    @PostMapping("/upDateMovement")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes) { //
        if (movimientosService.guardarOrActualizarTransacciones(mov)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/movements";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/editMovement/" + mov.getIdTransaccion();
    }

    // Metodo para encriptar contraseñas
    /**@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/


}

//Metodos para el @RESTCONTROLLER
/**
 @PostMapping(path = "/enterprises/{id}/movements")
 public String crearNuevoMoviemtoEmpresa (@PathVariable("id") Long idEmpresa, @RequestBody MovimientoDinero movimiento){
 boolean existeEmpresa = movimientosService.llaveEmpresa(idEmpresa);
 if((existeEmpresa == true) && (idEmpresa == movimiento.getEmpresa().getIdEmpresa())){
 MovimientoDinero transaccionTemp = new MovimientoDinero();
 transaccionTemp = movimientosService.capturarTransaccion(movimiento);
 Empresa emp = empresaService.buscarEmpresaId(idEmpresa);
 String nombreEmp = emp.getNombreEmpresa();
 return "La transanccion se registro con exito a la empresa: " +nombreEmp;
 } else if ((existeEmpresa == true) && (idEmpresa != movimiento.getEmpresa().getIdEmpresa())) {
 return "La transanccion NO se puede registrar, el idEmpresa de la transaccion suministrado NO corresponde a la empresa Seleccionado";
 }
 return "La Empresa Seleccionada no se encuentra registrada en la base de datos de la plataforma";
 }

 //Editar o actualizar un movimiento
 @PatchMapping("/enterprises/{id}/movements")
 public String actualizarMovimiento(@PathVariable("id") Long idEmpresa, @RequestBody MovimientoDinero movimiento) {
 boolean existeEmpresa = movimientosService.llaveEmpresa(idEmpresa);
 if((existeEmpresa == true) && (idEmpresa == movimiento.getEmpresa().getIdEmpresa())){
 MovimientoDinero transaccionTemp = new MovimientoDinero();
 transaccionTemp.setTipoTransaccion(movimiento.getTipoTransaccion());
 transaccionTemp.setMontoTransaccion(movimiento.getMontoTransaccion());
 transaccionTemp.setConceptoTransaccion (movimiento.getConceptoTransaccion());
 transaccionTemp = movimientosService.capturarTransaccion(movimiento);
 Empresa emp = empresaService.buscarEmpresaId(idEmpresa);
 String nombreEmp = emp.getNombreEmpresa();
 return "La transanccion se actualizo con exito a la empresa: " +nombreEmp;
 } else if ((existeEmpresa == true) && (idEmpresa != movimiento.getEmpresa().getIdEmpresa())) {
 return "La transanccion NO se puede actualizar, el idEmpresa de la transaccion suministrado NO corresponde a la empresa Seleccionado";
 }
 return "La Empresa Seleccionada no se encuentra registrada en la base de datos de la plataforma";
 }

 //Consultar todos los movimientos
 @GetMapping(path = "/enterprises/{id}/movements")
 public List<MovimientoDinero> conultarMovimientosPorEmpresa(@PathVariable("id") Long idEmpresa) {
 return movimientosService.obtenerPorEmpresa(idEmpresa);
 }

 @DeleteMapping(path = "/enterprises/{id}/movements")
 public String deleteMovimiento(@PathVariable("id") Long idEmpresa, @RequestBody MovimientoDinero movimiento) {
 boolean existeEmpresa = movimientosService.llaveEmpresa(idEmpresa);
 if ((existeEmpresa == true) && (idEmpresa == movimiento.getEmpresa().getIdEmpresa())) {
 Long idTranTemp = movimiento.getIdTransaccion();
 boolean respuesta = movimientosService.deleteMovimiento(idTranTemp);
 if (respuesta) {
 return "Se elimino correctamente el movimiento con id " + idEmpresa;            }
 return "No se pudo eliminar el movimiento con id " + idEmpresa;
 }
 return "La Empresa Seleccionada no se encuentra registrada en la base de datos de la plataforma";
 }

 @GetMapping(path = "/enterprises/movements")
 public List<MovimientoDinero> verMovimientos() {
 return movimientosService.getAllMovimientos();
 }*/