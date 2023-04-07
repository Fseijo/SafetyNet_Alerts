package com.SafetyNetAlerts.App.service;


import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.model.Person;
import com.SafetyNetAlerts.App.repository.FireStRepository;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import com.SafetyNetAlerts.App.repository.PersonRepository;
import com.SafetyNetAlerts.App.service.dto.FireDTO;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

      private final PersonRepository personRepository;
      private final MedicalRecRepository medicalRecRepository;
      private final FireStRepository fireStRepository;


      public PersonService(PersonRepository personRepository, MedicalRecRepository medicalRecRepository, FireStRepository fireStRepository) {
            this.personRepository = personRepository;
            this.medicalRecRepository = medicalRecRepository;
            this.fireStRepository = fireStRepository;
      }


      public List<String> findAllEmailsByCity(String city) {
            List<Person> personList = personRepository.findAllPersons();
            List<String> emails = new ArrayList<>();

            for (Person person : personList) {
                  if (person.getCity().contentEquals(city)) {
                        emails.add(person.getEmail());
                  }
            }

            return emails;
      }

      //Retourner une liste des numéros de téléphone des résidents proches FireStation (forEach)
      public List<String> findAllPhoneNumbersByFireStation(String firestationToFind) {

            List<Person> personList = personRepository.findAllPersons();
            List<FireStation> fireStationsList = fireStRepository.findAllFireStations();
            List<String> phones = new ArrayList<>();

            for (Person person : personList) {
                  for (FireStation fireStation : fireStationsList) {
                        if (fireStation.getStation().equals(firestationToFind) && fireStation.getAddress().equals(person.getAddress())) {
                              phones.add(person.getPhone());
                        }
                  }
            }
            return phones;
      }

      public List<FireDTO> getListFireDTO(String address) {

            List<FireDTO> fireDTOList = new ArrayList<>();
            List<Person> personList = personRepository.findAllPersonsByAddress(address);
            List<FireStation> stationList = fireStRepository.findAllFireStationsByAddress(address);
            List<String> stationsNumbers =  stationList.stream().map(FireStation::getStation).collect(Collectors.toList());

            for (Person person : personList) {
                  MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                  String age = calculateAge(medicalRecord.getBirthdate());
                  FireDTO fireDTO = new FireDTO(person.getLastName(),
                          person.getPhone(),
                          age,
                          medicalRecord.getMedications(),
                          medicalRecord.getAllergies(),stationsNumbers);
                  fireDTOList.add(fireDTO);
            }
            return fireDTOList;

      }

      public String calculateAge(String birthdate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.now();
            LocalDate bdDate = LocalDate.parse(birthdate, formatter);
            int age = Period.between(bdDate, localDate).getYears();

            return Integer.toString(age);
      }

}
