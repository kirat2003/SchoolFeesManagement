package org.togo.rikCorpSolution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class GestionDuRecouvrementDesFraisScolairesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDuRecouvrementDesFraisScolairesApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
			accountService.addNewRole(new AppRole("SECRETAIRE"));
			accountService.addNewRole(new AppRole("DIRECTEUR"));
			accountService.addNewRole(new AppRole("ADMIN"));

			accountService.addNewUser(new AppUser("SecretaireA1","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser("Directeur","1234",new ArrayList<>()));
			accountService.addNewUser(new AppUser("Admin_Tarik","1234",new ArrayList<>()));

			accountService.addRoleToUser("SecretaireA1","SECRETAIRE");
			accountService.addRoleToUser("Directeur","DIRECTEUR");
			accountService.addRoleToUser("Admin_Tarik","SECRETAIRE");
			accountService.addRoleToUser("Admin_Tarik","DIRECTEUR");
			accountService.addRoleToUser("Admin_Tarik","ADMIN");
		};
	}*/
}
