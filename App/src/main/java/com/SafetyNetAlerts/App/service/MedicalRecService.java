package com.SafetyNetAlerts.App.service;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Service
public class MedicalRecService {

      private final MedicalRecRepository medicalRecRepository;
      private final ReadJsonFile readJsonFile;

      public MedicalRecService(MedicalRecRepository medicalRecRepository, ReadJsonFile readJsonFile) {
            this.medicalRecRepository = medicalRecRepository;
            this.readJsonFile = readJsonFile;
      }


      //Ajouter un nouveau dossier medical a la liste
      public ResponseEntity<MedicalRecord> saveNewMedicalRecord(MedicalRecord medicalRecord) {
            medicalRecRepository.findAllMedicalRecords().add(medicalRecord);
            if (Objects.isNull(medicalRecord)) {
                  return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}").buildAndExpand(medicalRecord.getFirstName()).toUri();
            return ResponseEntity.created(location).build();
      }

      //Mettre à jour un dossier medical

      public void updateAnExistingMedicalRec(MedicalRecord medicalRecord){
            List<MedicalRecord> medicalRecordList = medicalRecRepository.findAllMedicalRecords();

            for (MedicalRecord medRec : medicalRecordList){
                  if (medRec.getLastName().equals(medicalRecord.getLastName()) && medRec.getFirstName().equals(medicalRecord.getFirstName()));
                  medRec.setMedications(medicalRecord.getMedications());
                  medRec.setAllergies(medicalRecord.getAllergies());
            }
            readJsonFile.getData().setMedicalRecords(medicalRecordList);
      }

      //Supprimer un dossier medical

      public void deleteMedicalRecByFirstNameAndLastName(MedicalRecord medicalRecord){
            List<MedicalRecord> medicalRecordList = medicalRecRepository.findAllMedicalRecords();

            medicalRecordList.removeIf(m-> m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName()));
            readJsonFile.getData().setMedicalRecords(medicalRecordList);
      }
}
