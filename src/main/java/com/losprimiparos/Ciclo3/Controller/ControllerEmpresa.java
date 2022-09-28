package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import com.losprimiparos.Ciclo3.Service.Implements.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ControllerEmpresa {

    @Autowired
    EmpresaService empresaService;

    //Metodos para el @CONTROLLER

    /**@GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/app")
    public String presentacion(){
        return "presentacion";
    }*/


    @GetMapping("/enterprises")
    public String todasLasEmpresas(Model model){
        List<Empresa> listempresas = empresaService.consultarEmpresas();
        model.addAttribute("listempresas", listempresas);
        return "verEmpresas";
    }
    @GetMapping("/formEnterpris")
    public String registrarEmpresa(Model model){
        model.addAttribute("empresa", new Empresa());
        return "formularioEmpresa";
    }

    @PostMapping("/saveEnterprises")
    public String guardarEmpresa(Empresa empresa){
        empresaService.crearEmpresa(empresa);
        if(empresaService.crearEmpresa(empresa)==true){
            return "redirect:/enterprises";
        }
        return "redirect:/formEnterpris";
    }

    @GetMapping("/enterpris/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id){
        Optional<Empresa> empresaActualizar = empresaService.buscarEmpresaId(id);
        //Empresa emp = new Empresa();
        model.addAttribute("empresa",empresaActualizar);
        return "editarEmpresa";
    }

    @GetMapping("/deleteEnterpris/{id}")
    public String borrarEmpresa(@PathVariable Integer id, Model model){
        empresaService.eliminarEmpresa(id);
        return "redirect:/enterprises";
    }




    /**
    @GetMapping({"/VerEmpresas"})
    public String verEmpresas(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listempresas = empresaService.consultarEmpresas();
        model.addAttribute("empresaList", listempresas);
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa empresa = new Empresa();
        model.addAttribute("empresa",empresa);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa empresa, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(empresa)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Long idEmpresa, @ModelAttribute("mensaje") String mensaje){
        Empresa empresa=empresaService.buscarEmpresaId(idEmpresa);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("empresa",empresa);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("empresa") Empresa empresa, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(empresa)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpresa/"+empresa.getIdEmpresa();

    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Long idEmpresa, RedirectAttributes redirectAttributes){
        if (empresaService.eliminarEmpresa(idEmpresa)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpresas";
    }*/

    // Metodos para el @RESTCONTROLLER
   /** @GetMapping({"/enterprises"})
    public List<Empresa> verEmpresas() {
        return empresaService.consultarEmpresas();
    }

    //Guardar el json del body como una nueva empresa o registro en nuestra bd
    @PostMapping("/enterprises")
    public boolean guardarEmpresa(@RequestBody Empresa empresa) {
        return this.empresaService.saveOrUpdateEmpresa(empresa);
    }

    @GetMapping("/enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Long idEmpresa) {
        return this.empresaService.buscarEmpresaId(idEmpresa);
    }

    @PatchMapping("/enterprises/{id}")
    public boolean actualizarEmpresa(@PathVariable("id") Long idEmpresa, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.buscarEmpresaId(idEmpresa);
        emp.setNombreEmpresa(empresa.getNombreEmpresa());
        emp.setNitEmpresa(empresa.getNitEmpresa());
        emp.setDireccionEmpresa(empresa.getDireccionEmpresa());
        emp.setTelefonoEmpresa(empresa.getTelefonoEmpresa());
        return  empresaService.saveOrUpdateEmpresa(emp);

    }
    //Eliminar registro de la bd
    @DeleteMapping ("enterprises/{id}")
    public String EliminarEmpresa(@PathVariable("id") Long idEmpresa){
        boolean respuesta= this.empresaService.eliminarEmpresa(idEmpresa);
        if (respuesta){  //Si respuesta es true?
            return "No se pudo eliminar la empresa con id = " +idEmpresa;
        }
        else{
            return "Se elimino la empresa con id = "+idEmpresa;
        }
    }*/
}