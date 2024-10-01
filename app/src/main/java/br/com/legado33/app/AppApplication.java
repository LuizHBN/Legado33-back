package br.com.legado33.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	//TODO -> Construir global exception Handler;
	//TODO -> Verificar se os métodos FindById irão continuar retornando reads
	//TODO -> Requisição de transações filtradas por user
	//TODO -> Completar collections no Insomnia
	//TODO -> Retornando 500 Internal para User not found with ID
	//TODO -> Update de user não esta funcionando
	//TODO -> Update de acesso não esta atualizando a descrição

}

