package ma.enset.tp3_mvc;

import ma.enset.tp3_mvc.entities.Patient;
import ma.enset.tp3_mvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
