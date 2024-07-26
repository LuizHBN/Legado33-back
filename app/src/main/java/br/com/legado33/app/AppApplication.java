package br.com.legado33.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	//TODO -> Construir global exception Handler, verificar consistência de nomenclatura de endpoints;
	//TODO -> Verificar se os métodos FindById irão continuar retornando reads
}

