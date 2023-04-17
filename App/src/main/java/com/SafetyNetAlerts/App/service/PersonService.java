package com.SafetyNetAlerts.App.service;


import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.model.Person;
import com.SafetyNetAlerts.App.repository.FireStRepository;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import com.SafetyNetAlerts.App.repository.PersonRepository;
import com.SafetyNetAlerts.App.service.dto.ChildAlertDTO;
import com.SafetyNetAlerts.App.service.dto.FireDTO;
import com.SafetyNetAlerts.App.service.dto.PersonInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class PersonService {

      private final PersonRepository personRepository;
      private final MedicalRecRepository medicalRecRepository;
      private final FireStRepository fireStRepository;
      private final ReadJsonFile readJsonFile;


      public PersonService(PersonRepository personRepository, MedicalRecRepository medicalRecRepository, FireStRepository fireStRepository, ReadJsonFile readJsonFile) {
            this.personRepository = personRepository;
            this.medicalRecRepository = medicalRecRepository;
            this.fireStRepository = fireStRepository;
            this.readJsonFile = readJsonFile;
      }

      //Methode pour calculer l'age en String
      public String calculateAge(String birthdate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.now();
            LocalDate bdDate = LocalDate.parse(birthdate, formatter);
            int age = Period.between(bdDate, localDate).getYears();

            return Integer.toString(age);
      }


      // Retourner une liste d'emails par ville
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

      //Retourner une liste des numéros de téléphone des résidents proches FireStation
      public List<String> findAllPhoneNumbersByFireStation(String fireStationToFind) {

            List<Person> personList = personRepository.findAllPersons();
            List<FireStation> fireStationsList = fireStRepository.findAllFireStations();
            List<String> phones = new ArrayList<>();

            for (Person person : personList) {
                  for (FireStation fireStation : fireStationsList) {
                        if (fireStation.getStation().equals(fireStationToFind) && fireStation.getAddress().equals(person.getAddress())) {
                              phones.add(person.getPhone());
                        }
                  }
            }
            return phones;
      }

      // Retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la FStation
      public List<FireDTO> getListFireDTO(String address) {

            List<FireDTO> fireDTOList = new ArrayList<>();
            List<Person> personList = personRepository.findAllPersonsByAddress(address);
            List<FireStation> stationList = fireStRepository.findAllFireStationsByAddress(address);
            List<String> stationsNumbers = stationList.stream().map(FireStation::getStation).collect(Collectors.toList());

            for (Person person : personList) {
                  MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                  String age = calculateAge(medicalRecord.getBirthdate());
                  FireDTO fireDTO = new FireDTO(person.getLastName(), person.getPhone(), age, medicalRecord.getMedications(), medicalRecord.getAllergies(), stationsNumbers);
                  fireDTOList.add(fireDTO);
            }
            return fireDTOList;

      }

      // Retourner une liste d'enfants par adresse
      public List<ChildAlertDTO> getListChildAlertDTO(String address) {
            List<ChildAlertDTO> childAlertDTOList = new ArrayList<>();
            List<Person> personList = personRepository.findAllPersonsByAddress(address);

            for (Person person : personList) {
                  MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                  String age = calculateAge(medicalRecord.getBirthdate());
                  int ageInt = Integer.parseInt(age);
                  if (ageInt <= 18) {
                        List<Person> personList1 = personRepository.findFamilyMembersByLastName(person.getFirstName(), person.getLastName());
                        List<String> familyMembersList = personList1.stream().map(Person::getFirstName).collect(Collectors.toList());
                        ChildAlertDTO childAlertDTO = new ChildAlertDTO(person.getFirstName(), person.getLastName(), age, familyMembersList);
                        childAlertDTOList.add(childAlertDTO);
                  }
            }
            return childAlertDTOList;
      }

      // Retourner les informations de chaque personne

      public List<PersonInfoDTO> getListPersonInfoDTO(String firstName, String lastName) {
            List<PersonInfoDTO> personInfoDTOList = new ArrayList<>();
            List<Person> personList = personRepository.findAllPersonsByFirstNameAndLastName(firstName, lastName);

            for (Person person : personList) {
                  MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                  String age = calculateAge(medicalRecord.getBirthdate());
                  PersonInfoDTO personInfoDTO = new PersonInfoDTO(person.getLastName(), person.getAddress(), age, person.getEmail(), medicalRecord.getMedications(), medicalRecord.getAllergies());
                  personInfoDTOList.add(personInfoDTO);
            }
            return personInfoDTOList;
      }

      //Ajouter une nouvelle personne a la liste
      public ResponseEntity<Person> saveNewPerson(Person person) {
            personRepository.findAllPersons().add(person);
            if (Objects.isNull(person)) {
                  return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName}").buildAndExpand(person.getFirstName()).toUri();
            return ResponseEntity.created(location).build();
      }

      //Mettre à jour une personne de la liste
      public void updateAnExistingPerson(Person person) {
            List<Person> personList = personRepository.findAllPersons();

            for (Person person1 : personList) {
                  if (person1.getLastName().equals(person.getLastName()) && person1.getFirstName().equals(person.getFirstName())) {
                        person1.setPhone(person.getPhone());
                        person1.setEmail(person.getEmail());
                  }
            }
            readJsonFile.getData().setPersons(personList);
      }

      //Supprimer une personne
      public void deletePersonByFirstNameAndLastName(Person person) {
            List<Person> personList = personRepository.findAllPersons();

            personList.removeIf(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()));

            readJsonFile.getData().setPersons(personList);
      }
}
