package com.SafetyNetAlerts.App.service.dto;

public class PersonInfoDTO {

      private String lastName;
      private String address;
      private String age;
      private String email;
      private String[] medications;
      private String[] allergies;

      public PersonInfoDTO(String lastName, String address, String age, String email, String[] medications, String[] allergies) {
            this.lastName = lastName;
            this.address = address;
            this.age = age;
            this.email = email;
            this.medications = medications;
            this.allergies = allergies;
      }

      public String getLastName() {
            return lastName;
      }

      public void setLastName(String lastName) {
            this.lastName = lastName;
      }

      public String getAddress() {
            return address;
      }

      public void setAddress(String address) {
            this.address = address;
      }

      public String getAge() {
            return age;
      }

      public void setAge(String age) {
            this.age = age;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String[] getMedications() {
            return medications;
      }

      public void setMedications(String[] medications) {
            this.medications = medications;
      }

      public String[] getAllergies() {
            return allergies;
      }

      public void setAllergies(String[] allergies) {
            this.allergies = allergies;
      }
}
