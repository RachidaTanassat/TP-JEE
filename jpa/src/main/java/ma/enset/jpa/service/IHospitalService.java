package ma.enset.jpa.service;

import ma.enset.jpa.entities.Consultation;
import ma.enset.jpa.entities.Medecin;
import ma.enset.jpa.entities.Patient;
import ma.enset.jpa.entities.RendezVous;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
