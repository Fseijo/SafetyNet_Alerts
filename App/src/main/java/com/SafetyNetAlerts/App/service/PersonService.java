package com.SafetyNetAlerts.App.service;


import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.Person;
import com.SafetyNetAlerts.App.repository.FireStRepository;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import com.SafetyNetAlerts.App.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PersonService {

      private final PersonRepository personRepository;
      private final MedicalRecRepository medicalRecRepository;
      private final FireStRepository fireStRepository;


      public PersonService (PersonRepository personRepository, MedicalRecRepository medicalRecRepository, FireStRepository fireStRepository){
            this.personRepository = personRepository;
            this.medicalRecRepository = medicalRecRepository;
            this.fireStRepository = fireStRepository;
      }


      public List<String> findAllEmailsByCity(String city){
            List<Person> personList = personRepository.findAllPersons();
            List<String> emails = new ArrayList<String>();

            for (Person person : personList){
                  if (person.getCity().contentEquals(city)){
                        emails.add(person.getEmail());
                  }
            }

            return emails;
      }

      //Retourner une liste des numéros de téléphone des résidents proches FireStation (forEach)
      public List<String> findAllPhoneNumbersByFireStation(String firestationToFind){

            List<Person> personList = personRepository.findAllPersons();
            List<FireStation> fireStationsList = fireStRepository.findAllFireStations();
            List<String> phones = new ArrayList<String>();

            for (Person person : personList){
                  for (FireStation fireStation : fireStationsList){
                        if (fireStation.getStation().equals(firestationToFind)&&fireStation.getAddress().equals(person.getAddress())){
                              phones.add(person.getPhone());
                        }
                  }
            }
            return phones;
      }
      //Retourner une liste des numéros de téléphone des résidents proches FireStation (Stream)

      public List<String> findAllPhoneByFireStation(String station){
          List<Person> persons = personRepository.findAllPersons().stream().collect(Collectors.toList());

            List<String> addresses = fireStRepository.findAllFireStations().stream()
                   .filter(fs -> fs.getStation().equals(station))
                   .map(fs -> fs.getAddress()).collect(Collectors.toList());

            List<String> phoneNumbers = persons.stream().filter(addresses ::contains).map(p -> p.getPhone()).collect(Collectors.toList());


          return phoneNumbers;
      }



}
