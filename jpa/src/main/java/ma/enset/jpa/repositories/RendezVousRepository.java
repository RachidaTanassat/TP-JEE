package ma.enset.jpa.repositories;

import ma.enset.jpa.entities.Patient;
import ma.enset.jpa.entities.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {



}
