package ma.enset.tp3_mvc;

import ma.enset.tp3_mvc.entities.Patient;
import ma.enset.tp3_mvc.repositories.PatientRepository;
import ma.enset.tp3_mvc.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
public class Tp3MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp3MvcApplication.class, args);
	}

//	@Bean
	CommandLineRunner start(PatientRepository patientRepository) {
		return args -> {
			Stream.of("Mohammed", "Hassan", "Najat")
					.forEach(name->{
						Patient patient=new Patient();
						patient.setName(name);
						patient.setMalade(false);
						patient.setDateNaissance(new Date());
						patient.setScore(120);
						patientRepository.save(patient);
					});
			patientRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});

		};

	}

	//@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder = passwordEncoder();
		return  args -> {

			UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
			if(u1==null) {
				jdbcUserDetailsManager.createUser(
						User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
				);
			}
			UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
			if(u2==null) {
				jdbcUserDetailsManager.createUser(
						User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
				);
			}
			UserDetails admin = jdbcUserDetailsManager.loadUserByUsername("admin2");
			if(admin==null) {
				jdbcUserDetailsManager.createUser(
						User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
				);
			}
		};
	}
	//@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		return args -> {
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");
			accountService.addNewUser("user1", "1234", "user1@gmail.com", "1234");
			accountService.addNewUser("user2", "1234", "user2@gmail.com", "1234");
			accountService.addNewUser("admin", "1234", "admin@gmail.com", "1234");

			accountService.addRoleToUser("user1", "USER");
			accountService.addRoleToUser("user2", "USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");


		};
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
