package com.SafetyNetAlerts.App.service.dto;

import java.util.List;

public class FloodStationsDTO {
      private String address;
      private List<FloodStationsDTOPersons> floodStationsDTOPersons;

      public static class FloodStationsDTOPersons{
            private String lastName;
            private String phone;
            private String age;
            private String [] medications;
            private String [] allergies;

            public FloodStationsDTOPersons(String lastName, String phone, String age, String[] medications, String[] allergies) {
                  this.lastName = lastName;
                  this.phone = phone;
                  this.age = age;
                  this.medications = medications;
                  this.allergies = allergies;
            }

            public String getLastName() {
                  return lastName;
            }

            public void setLastName(String lastName) {
                  this.lastName = lastName;
            }

            public String getPhone() {
                  return phone;
            }

            public void setPhone(String phone) {
                  this.phone = phone;
            }

            public String getAge() {
                  return age;
            }

            public void setAge(String age) {
                  this.age = age;
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


      public FloodStationsDTO() {
      }

      public FloodStationsDTO(String address, List<FloodStationsDTOPersons> floodStationsDTOPersons) {
            this.address = address;
            this.floodStationsDTOPersons = floodStationsDTOPersons;
      }

      public String getAddress() {
            return address;
      }

      public void setAddress(String address) {
            this.address = address;
      }

      public List<FloodStationsDTOPersons> getFloodStationsDTOPersons() {
            return floodStationsDTOPersons;
      }

      public void setFloodStationsDTOPersons(List<FloodStationsDTOPersons> floodStationsDTOPersons) {
            this.floodStationsDTOPersons = floodStationsDTOPersons;
      }
}
