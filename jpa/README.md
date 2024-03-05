### Activité Pratique N°2 : ORM, Jpa, Hibernate Spring Data

## La conception du projet
<img width="669" alt="1" src="https://github.com/RachidaTanassat/TP-JEE/assets/85264433/abba114c-cb6e-4250-a53c-bc42ad869b7d">

## La structure du projet
<img width="466" alt="2" src="https://github.com/RachidaTanassat/TP-JEE/assets/85264433/9643e5a5-a930-4201-b49f-536360c1fecb">

## JpaApplication
```java

@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService, PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository){
        return args ->{
            Stream.of("Mohammed", "Hassan", "Najat")
                    .forEach(name->{
                        Medecin medecin=new Medecin();
                        medecin.setName(name);
                        medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
                        medecin.setEmail(name+"@gmail.com");
                        medecinRepository.save(medecin);
                    });
            Stream.of("Mohammed", "Hassan", "Najat")
                    .forEach(name->{
                        Patient patient=new Patient();
                        patient.setName(name);
                        patient.setMalade(false);
                        patient.setScore(20);
                        patientRepository.save(patient);
                    });
            Patient patient = patientRepository.findById(1L).orElse(null);
            Patient patient1 = patientRepository.findByName("Hassan");
            Medecin medecin = medecinRepository.findByName("Najat");
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            rendezVousRepository.save(rendezVous);
            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(new Date());
            consultation.setRendezVous(rendezVous1);
            consultation.setRapport("rapport de consultation");
            consultationRepository.save(consultation);
            User user = new User();
            user.setUserName("user1");
            user.setPassword("1243");
            userService.addNewUser(user);
            User user2 = new User();
            user2.setUserName("user2");
            user2.setPassword("1243");
            userService.addNewUser(user2);
            Stream.of("STUDENT", "USER", "ADMIN").forEach(r->{
                Role role = new Role();
                role.setRoleName(r);
                userService.addNewRole(role);
            });
            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user2", "ADMIN");
            try {
                User user3 = userService.authenticate("user1", "1243");
                System.out.println(user3.getUserId());
                System.out.println(user3.getUserName());
                System.out.println("Roles =>");
                user3.getRoles().forEach(r->{
                    System.out.println("Role=>"+ r);
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }
}


