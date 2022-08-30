package com.losprimiparos.Ciclo3;

import com.losprimiparos.Ciclo3.Modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
public class Ciclo3Application {

	@GetMapping("/hello")
	public String hello(){
		return "Hola... vas bien!!!, etamos empezando.... perseva saldras vivo de esto!!!!";
	}

	@GetMapping("/test")
	public String test(){
		Empresa emp = new Empresa("Servimorel Ltda.", "820.003.414-4", "Calle # 10 -40, Miraflores - Boyaca", "gerencia@servimorel.com", "3103025461");
		return emp.getNombreEmpresa();
	}


	public static void main(String[] args) {

		SpringApplication.run(Ciclo3Application.class, args);

	}

}
