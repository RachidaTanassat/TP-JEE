package ma.enset.tp3_mvc.repositories;

import ma.enset.tp3_mvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
   Page<Patient> findByNameContains(String keyword, Pageable pageable);
}
