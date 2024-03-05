package ma.enset.jpa.repositories;

import ma.enset.jpa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByName(String name);
    public List<Patient> findByMalade(Boolean m);
    Page<Patient> findByMalade(Boolean m, Pageable pageable);

}
