package com.SafetyNetAlerts.App.service.dto;


import java.util.List;

public class FireStationDto {

      private Integer adultes;
      private Integer enfants;
      private List<FireStationDtoPerson> fireStationDtoPersonList;


      public static class FireStationDtoPerson {

            private String firstName;
            private String lastName;
            private String address;
            private String phone;


            public FireStationDtoPerson(String firstName, String lastName, String address, String phone) {
                  this.firstName = firstName;
                  this.lastName = lastName;
                  this.address = address;
                  this.phone = phone;
            }

            public String getFirstName() {
                  return firstName;
            }

            public void setFirstName(String firstName) {
                  this.firstName = firstName;
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

            public String getPhone() {
                  return phone;
            }

            public void setPhone(String phone) {
                  this.phone = phone;
            }
      }



      public FireStationDto(Integer adultes, Integer enfants, List<FireStationDtoPerson> fireStationDtoPersonList) {
            this.adultes = adultes;
            this.enfants = enfants;
            this.fireStationDtoPersonList = fireStationDtoPersonList;
      }

      public Integer getAdultes() {
            return adultes;
      }

      public void setAdultes(Integer adultes) {
            this.adultes = adultes;
      }

      public Integer getEnfants() {
            return enfants;
      }

      public void setEnfants(Integer enfants) {
            this.enfants = enfants;
      }

      public List<FireStationDtoPerson> getFireStationDTOpersonList() {
            return fireStationDtoPersonList;
      }

      public void setFireStationDTOpersonList(List<FireStationDtoPerson> fireStationDtoPersonList) {
            this.fireStationDtoPersonList = fireStationDtoPersonList;
      }
}