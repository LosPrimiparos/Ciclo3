package com.losprimiparos.Ciclo3.Controller;

import com.losprimiparos.Ciclo3.Modelos.Empleado;
import com.losprimiparos.Ciclo3.Service.Implements.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FrontController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal OidcUser principal){
        if (principal != null){
            Empleado user = this.empleadoService.capturarOrCreacionUsuario(principal.getClaims());
            model.addAttribute("user", user);

        }
        return "index";
    }

    @GetMapping("/app")
    public String entrada(){
        return "presentacion";
    }
}
