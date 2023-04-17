package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import com.SafetyNetAlerts.App.service.MedicalRecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecController {

      private final MedicalRecRepository medicalRecRepository;
      private final MedicalRecService medicalRecService;

      public MedicalRecController(MedicalRecRepository medicalRecRepository, MedicalRecService medicalRecService) {
            this.medicalRecRepository = medicalRecRepository;
            this.medicalRecService = medicalRecService;
      }

      @GetMapping("/medicalRecord")
      public List<MedicalRecord> listOfAllMedicalRecords(){
            return medicalRecRepository.findAllMedicalRecords();
      }

      @PostMapping("/medicalRecord")
      public ResponseEntity<MedicalRecord> postNewMedicalRecord(@RequestBody MedicalRecord medicalRecord){
            ResponseEntity<MedicalRecord> medicalRecordResponseEntity = medicalRecService.saveNewMedicalRecord(medicalRecord);
            return medicalRecordResponseEntity;
      }
      @PutMapping("/medicalRecord")
      public void updateMedicalRecordFromTheList(@RequestBody MedicalRecord medicalRecord){
            medicalRecService.updateAnExistingMedicalRec(medicalRecord);
      }
      @DeleteMapping("/medicalRecord")
      public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord){
            medicalRecService.deleteMedicalRecByFirstNameAndLastName(medicalRecord);
      }
}
