package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecController {

      private final MedicalRecRepository medicalRecRepository;

      public MedicalRecController(MedicalRecRepository medicalRecRepository) {
            this.medicalRecRepository = medicalRecRepository;
      }

      @GetMapping("/medicalRecord")
      public List<MedicalRecord> listOfAllMedicalRecords(){
            return medicalRecRepository.findAllMedicalRecords();
      }
}
