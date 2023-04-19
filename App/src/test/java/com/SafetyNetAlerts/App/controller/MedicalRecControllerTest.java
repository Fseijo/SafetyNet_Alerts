package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MedicalRecControllerTest {
      @Autowired
      MedicalRecController medicalRecController;
      @Autowired
      ReadJsonFile readJsonFile;

      @Test
      void listOfAllMedicalRecords() {
            List<String> medicalRecordList = medicalRecController.listOfAllMedicalRecords().stream().map(MedicalRecord::getBirthdate).collect(Collectors.toList());
            assertNotNull(medicalRecordList);
            assertThat(medicalRecordList).contains("03/06/1984");
      }

      @Test
      void postNewMedicalRecord() {
            String[] medications = {"hydrapermazol:300mg"};
            String[] allergies = {"dodoxadin:30mg", "pharmacol:5000mg", "terazine:500mg", "shellfish"};
            MedicalRecord newMedicalRecord = new MedicalRecord("George", "Marron", "03/06/1994", medications, allergies);
            medicalRecController.postNewMedicalRecord(newMedicalRecord);
            MedicalRecord medicalRecord = readJsonFile.getData().getMedicalRecords().stream().filter(m -> m.getBirthdate().equals(newMedicalRecord.getBirthdate()) && (m.getLastName().equals(newMedicalRecord.getLastName()))).findFirst().get();
            assertThat(medicalRecord.getBirthdate()).isEqualTo(newMedicalRecord.getBirthdate());
            assertThat(medicalRecord.getLastName()).isEqualTo(newMedicalRecord.getLastName());
      }

      @Test
      void updateMedicalRecordFromTheList() {
            String[] medications = {"hydrapermazol:300mg"};
            String[] allergies = {"dodoxadin:30mg", "pharmacol:5000mg", "terazine:500mg", "shellfish"};
            MedicalRecord newMedicalRecord = new MedicalRecord("George", "Marron", "03/06/1994", medications, allergies);
            medicalRecController.postNewMedicalRecord(newMedicalRecord);
            String[] newMedications = {"noxidian:100mg", "pharmacol:2500mg"};
            MedicalRecord updatedMedicalRecord = new MedicalRecord("George", "Marron", "03/06/1994", newMedications, allergies);
            medicalRecController.updateMedicalRecordFromTheList(updatedMedicalRecord);
            MedicalRecord medicalRecord = readJsonFile.getData().getMedicalRecords().stream()
                    .filter(m -> m.getFirstName().equals(updatedMedicalRecord.getFirstName()) && (m.getLastName().equals(updatedMedicalRecord.getLastName()))).findAny().get();
            assertThat(medicalRecord.getFirstName()).isEqualTo(updatedMedicalRecord.getFirstName());
            assertThat(medicalRecord.getMedications()).isEqualTo(updatedMedicalRecord.getMedications());
      }

      @Test
      void deleteMedicalRecord() {
            String[] medications = {"hydrapermazol:300mg"};
            String[] allergies = {"dodoxadin:30mg", "pharmacol:5000mg", "terazine:500mg", "shellfish"};
            MedicalRecord newMedicalRecord = new MedicalRecord("George", "Marron", "03/06/1994", medications, allergies);
            medicalRecController.postNewMedicalRecord(newMedicalRecord);
            readJsonFile.getData().getFireStations();
            medicalRecController.deleteMedicalRecord(newMedicalRecord);
            List<MedicalRecord> medicalRecordList = readJsonFile.getData().getMedicalRecords();
            assertEquals(23,medicalRecordList.size());
      }
}