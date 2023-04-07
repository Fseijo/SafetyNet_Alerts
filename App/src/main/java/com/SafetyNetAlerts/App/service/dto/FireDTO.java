package com.SafetyNetAlerts.App.service.dto;

import java.util.List;

public class FireDTO {
      private String lastName;
      private String phone;
      private String age;
      private String[] medications;
      private String[] allergies;
      private List<String> listStationNumber;

      public List<String> getListStationNumber() {
            return listStationNumber;
      }

      public String getLastName() {
            return lastName;
      }

      public String getPhone() {
            return phone;
      }

      public String getAge() {
            return age;
      }

      public String[] getMedications() {
            return medications;
      }

      public String[] getAllergies() {
            return allergies;
      }

      public FireDTO(String lastName, String phone, String age, String[] medications, String[] allergies, List<String> listStationNumber) {
            this.lastName = lastName;
            this.phone = phone;
            this.age = age;
            this.medications = medications;
            this.allergies = allergies;
            this.listStationNumber = listStationNumber;
      }

      public void setLastName(String lastName) {
            this.lastName = lastName;
      }

      public void setPhone(String phone) {
            this.phone = phone;
      }

      public void setAge(String age) {
            this.age = age;
      }

      public void setMedications(String[] medications) {
            this.medications = medications;
      }

      public void setAllergies(String[] allergies) {
            this.allergies = allergies;
      }

      public void setListStationNumber(List<String> listStationNumber) {
            this.listStationNumber = listStationNumber;
      }
}
