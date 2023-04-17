package com.SafetyNetAlerts.App.service;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.model.Person;
import com.SafetyNetAlerts.App.repository.FireStRepository;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import com.SafetyNetAlerts.App.repository.PersonRepository;
import com.SafetyNetAlerts.App.service.dto.FireStationDto;
import com.SafetyNetAlerts.App.service.dto.FloodStationsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FireStService {

      private final PersonRepository personRepository;
      private final FireStRepository fireStRepository;
      private final MedicalRecRepository medicalRecRepository;
      private final PersonService personService;
      private final ReadJsonFile readJsonFile;

      public FireStService(PersonRepository personRepository, FireStRepository fireStRepository, MedicalRecRepository medicalRecRepository, PersonService personService, ReadJsonFile readJsonFile) {
            this.personRepository = personRepository;
            this.fireStRepository = fireStRepository;
            this.medicalRecRepository = medicalRecRepository;
            this.personService = personService;
            this.readJsonFile = readJsonFile;
      }

      //Retourner une liste de tous les foyers desservis par la caserne ainsi que le trie par age
      public FireStationDto getFireStationDTO(String stationNumber) {

            List<FireStation> fireStationList = fireStRepository.findAllFireStationsByStationNumber(stationNumber);
            List<String> addressByStationNumber = fireStationList.stream().map(FireStation::getAddress).toList();
            List<FireStationDto.FireStationDtoPerson> fireStationDtoPersonList = new ArrayList<>();
            Integer adultesCount = 0;
            Integer childrenCount = 0;

            for (String fireStation : addressByStationNumber) {
                  List<Person> personListByStationAddress = personRepository.findAllPersonsByAddress(fireStation).stream().toList();

                  for (Person person : personListByStationAddress) {
                        MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                        String age = personService.calculateAge(medicalRecord.getBirthdate());
                        int ageInt = Integer.parseInt(age);
                        FireStationDto.FireStationDtoPerson fireStationDtoPerson = new FireStationDto.FireStationDtoPerson(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
                        fireStationDtoPersonList.add(fireStationDtoPerson);
                        if (ageInt <= 18) {
                              childrenCount++;
                        } else {
                              adultesCount++;
                        }
                  }
            }
            return new FireStationDto(adultesCount, childrenCount, fireStationDtoPersonList);
      }

      //Retourner une liste de tous les foyers desservis par la caserne
      public List<FloodStationsDTO> getFloodStationsDTO(List<String> stationNumbers) {

            List<FloodStationsDTO> floodStationsDTOListToReturn = new ArrayList<>();

            for (String stationNumber : stationNumbers) {
                  List<FireStation> fireStationList = fireStRepository.findAllFireStationsByStationNumber(stationNumber);
                  List<String> addressByStationNumber = fireStationList.stream().map(FireStation::getAddress).toList();

                  for (String address : addressByStationNumber) {
                        List<FloodStationsDTO.FloodStationsDTOPersons> floodStationsDTOPersonsList = new ArrayList<>();

                        List<Person> personListByStationAddress = personRepository.findAllPersonsByAddress(address).stream().toList();

                        for (Person person : personListByStationAddress) {
                              MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                              String age = personService.calculateAge(medicalRecord.getBirthdate());
                              floodStationsDTOPersonsList.add(new FloodStationsDTO.FloodStationsDTOPersons(person.getLastName(), person.getPhone(), age, medicalRecord.getMedications(), medicalRecord.getAllergies()));
                        }
                        floodStationsDTOListToReturn.add(new FloodStationsDTO(address, floodStationsDTOPersonsList));
                  }
            }
            return floodStationsDTOListToReturn;
      }

      //Ajouter une nouvelle station a la liste
      public ResponseEntity<FireStation> save(FireStation fireStation) {
            fireStRepository.findAllFireStations().add(fireStation);
            if (Objects.isNull(fireStation)) {
                  return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{address}").buildAndExpand(fireStation.getAddress()).toUri();
            return ResponseEntity.created(location).build();
      }

      //Mettre à jour une Station de la liste
      public void updateByStationNumber(FireStation fireStation) {
            List<FireStation> fireStationList = fireStRepository.findAllFireStations();

            for (FireStation fire : fireStationList) {
                  if (fire.getAddress().equals(fireStation.getAddress())) {
                        fire.setStation(fireStation.getStation());
                  }
            }
      }

      //Supprimer une Station
      public void deleteStation(FireStation fireStation) {
            List<FireStation> fireStationList = fireStRepository.findAllFireStations();

          fireStationList.removeIf(f->f.getStation().equals(fireStation.getStation())&& (f.getAddress().equals(fireStation.getAddress())));
          readJsonFile.getData().setFireStations(fireStationList);

      }


}
