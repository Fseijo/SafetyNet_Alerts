package com.SafetyNetAlerts.App.repository;


import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.MedicalRecords;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecRepository {

      private final ReadJsonFile readJsonFile;

      public MedicalRecRepository(ReadJsonFile readJsonFile) {
            this.readJsonFile = readJsonFile;
      }

      public List<MedicalRecords> findAllMedicalRecords(){
            return readJsonFile.getData().getMedicalRecords();
      }
}
