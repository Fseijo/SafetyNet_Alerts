package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.service.PersonService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonControllerTest {

      @Autowired
      PersonController personController;
      PersonService personService;


      @Test
      void listEmails() {
            List<String> listEmailByCity = Arrays.asList("aly@imail.com", "bstel@email.com", "clivfd@ymail.com", "drk@email.com", "gramps@email.com", "jaboyd@email.com", "jpeter@email.com", "lily@email.com", "reg@email.com", "soph@email.com", "ssanw@email.com", "tcoop@ymail.com", "tenz@email.com", "ward@email.com", "zarc@email.com");
            List<String> maListeMail = personController.listEmails("Culver");

            assertTrue(listEmailByCity.toString().equals(maListeMail.toString()));

      }

      @Test
      void listPhoneNumberByFireStation() {
            List<String> phoneList = personController.listPhoneNumberByFireStation("1");
            phoneList.size();
      }

      @Test
      void listPeopleByAddress() {
      }

      @Test
      void listChildAlertByAddress() {
      }

      @Test
      void listInformationsByPerson() {
      }

      @Test
      void listOfAllPersons() {
      }

      @Test
      void postNewPerson() {
      }

      @Test
      void updatePersonFromTheList() {
      }

      @Test
      void deletePerson() {
      }
}