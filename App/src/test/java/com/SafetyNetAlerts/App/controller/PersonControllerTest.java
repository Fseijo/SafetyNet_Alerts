package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class PersonControllerTest {

      @Autowired
      PersonController personController;
      @Autowired
      ReadJsonFile readJsonFile;


      @Test
      void listEmails() {
            //List<String> listEmailByCity = Arrays.asList("aly@imail.com", "bstel@email.com", "clivfd@ymail.com", "drk@email.com", "gramps@email.com", "jaboyd@email.com", "jpeter@email.com", "lily@email.com", "reg@email.com", "soph@email.com", "ssanw@email.com", "tcoop@ymail.com", "tenz@email.com", "ward@email.com", "zarc@email.com");
            List<String> maListeMail = personController.listEmails("Culver");
            assertNotNull(maListeMail);
            assertThat(maListeMail).contains("bstel@email.com");
            //assertEquals(listEmailByCity.toString(),(maListeMail.toString()));

      }

      @Test
      void listPhoneNumberByFireStation() {
            List<String> phoneList = personController.listPhoneNumberByFireStation("1");
            assertEquals(6, phoneList.size());
      }

      @Test
      void listPeopleByAddress() {
            List<String> phoneList = Arrays.asList("841-874-6512", "841-874-6513", "841-874-6512", "841-874-6512", "841-874-6544");
            List<String> fireDTOList = personController.listPeopleByAddress("1509 Culver St").stream().map(f -> f.getPhone()).toList();
            assertThat(fireDTOList).isEqualTo(phoneList);
      }

      @Test
      void listChildAlertByAddress() {
            List<String> childAlertDTOListIntoString = personController.listChildAlertByAddress("1509 Culver St").stream().map(c -> c.getAge()).toList();
            List<Integer> ageNumbers = new ArrayList<>();

            for (String age : childAlertDTOListIntoString) {
                  int ageInt = Integer.parseInt(age);
                  ageNumbers.add(ageInt);
            }
            assertTrue(18 >= ageNumbers.get(0));
            assertTrue(18 >= ageNumbers.get(1));

      }

      @Test
      void listInformationsByPerson() {
            String personInfoDTOListIntoString = personController.listInformationsByPerson("Tony", "Cooper").stream().map(p -> p.getEmail()).collect(Collectors.joining()).toString();
            String email = "tcoop@ymail.com";
            assertThat(personInfoDTOListIntoString).isEqualTo(email);
      }

      @Test
      void listOfAllPersons() {
            //List<String> maListOfFirstName = Arrays.asList("John", "Jacob", "Tenley", "Roger", "Felicia", "Jonanathan", "Tessa", "Peter", "Foster", "Tony", "Lily", "Sophia", "Warren", "Zach", "Reginold", "Jamie", "Ron", "Allison", "Brian", "Shawna", "Kendrik", "Clive", "Eric");
            List<String> firstNameListIntoString = personController.listOfAllPersons().stream().map(p -> p.getFirstName()).toList();
            assertNotNull(firstNameListIntoString);
            assertThat(firstNameListIntoString).contains("Tenley");
            //assertTrue(firstNameListIntoString.toString().equals(maListOfFirstName.toString()));
      }

      @Test
      void postNewPerson() {
            Person person = new Person("Patrick", "Dubois", "958 E. Rose Dr",
                    "Culver",
                    "97451",
                    "841-874-2615",
                    "pdubois@email.com");
            personController.postNewPerson(person);
            Person person1 = readJsonFile.getData().getPersons().stream().filter(p-> p.getEmail().equals(person.getEmail())).collect(Collectors.toList()).stream().findAny().get();
            assertEquals(person1.getEmail(),person.getEmail());
      }

      @Test
      void updatePersonFromTheList() {
            Person person = new Person("Patrick", "Dubois", "958 E. Rose Dr",
                    "Culver",
                    "97451",
                    "841-874-8888",
                    "ptrickdbois@email.com");
            personController.postNewPerson(person);
            personController.updatePersonFromTheList(person);
            Person person1 = readJsonFile.getData().getPersons().stream().filter(p-> p.getEmail().equals(person.getEmail())).findAny().get();
            assertEquals(person1.getEmail(),person.getEmail());
      }

      @Test
      void deletePerson() {
            Person person = new Person("Patrick", "Dubois", "958 E. Rose Dr",
                    "Culver",
                    "97451",
                    "841-874-8888",
                    "ptrickdbois@email.com");
            personController.postNewPerson(person);
            readJsonFile.getData().getPersons();
            personController.deletePerson(person);
            List<Person> personList = readJsonFile.getData().getPersons();

            assertEquals(23,personList.size());
      }
}