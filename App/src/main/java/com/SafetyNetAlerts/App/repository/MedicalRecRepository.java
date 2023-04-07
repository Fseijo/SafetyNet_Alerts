package com.SafetyNetAlerts.App.repository;


import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MedicalRecRepository {

      private final ReadJsonFile readJsonFile;

      public MedicalRecRepository(ReadJsonFile readJsonFile) {
            this.readJsonFile = readJsonFile;
      }

     public MedicalRecord findMedicalRecordByFirstnameAndLastname(String firstName, String lastName){
           return readJsonFile.getData().getMedicalRecords().stream()
                    .filter(m -> m.getFirstName().equals(firstName))
                    .filter(m -> m.getLastName().equals(lastName))
                    .findAny().get();
      }
}
