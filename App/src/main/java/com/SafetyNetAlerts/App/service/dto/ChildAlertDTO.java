package com.SafetyNetAlerts.App.service.dto;

import java.util.List;

public class ChildAlertDTO {
      private String firstName;
      private String lastName;
      private String age;
      private List<String> familyMembersList;


      public ChildAlertDTO(String firstName, String lastName, String age, List<String> familyMembersList) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.familyMembersList = familyMembersList;
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

      public String getAge() {
            return age;
      }

      public void setAge(String age) {
            this.age = age;
      }

      public List<String> getFamilyMembersList() {
            return familyMembersList;
      }

      public void setFamilyMembersList(List<String> familyMembersList) {
            this.familyMembersList = familyMembersList;
      }
}
