package com.SafetyNetAlerts.App.model;

import java.util.List;

public class Data {
      private List<Person> persons;
      private List<FireStation> fireStations;
      private List<MedicalRecords> medicalRecords ;

      public Data() {
      }

      public Data(List<Person> persons, List<FireStation> fireStations, List<MedicalRecords> medicalRecords) {
            this.persons = persons;
            this.fireStations = fireStations;
            this.medicalRecords = medicalRecords;
      }


      public List<Person> getPersons() {
            return persons;
      }

      public void setPersons(List<Person> persons) {
            this.persons = persons;
      }

      public List<FireStation> getFireStations() {
            return fireStations;
      }

      public void setFireStations(List<FireStation> fireStations) {
            this.fireStations = fireStations;
      }

      public List<MedicalRecords> getMedicalRecords() {
            return medicalRecords;
      }

      public void setMedicalRecords(List<MedicalRecords> medicalRecords) {
            this.medicalRecords = medicalRecords;
      }


      @Override
      public String toString() {
            return "Data{" + "personsList=" + persons + ", fireStations=" + fireStations + ", medicalRecords=" + medicalRecords + '}';
      }
}
