package ma.enset.jpa.service;

import ma.enset.jpa.entities.Consultation;
import ma.enset.jpa.entities.Medecin;
import ma.enset.jpa.entities.Patient;
import ma.enset.jpa.entities.RendezVous;
import ma.enset.jpa.repositories.ConsultationRepository;
import ma.enset.jpa.repositories.MedecinRepository;
import ma.enset.jpa.repositories.PatientRepository;
import ma.enset.jpa.repositories.RendezVousRepository;

public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;


    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
