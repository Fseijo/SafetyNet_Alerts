package com.SafetyNetAlerts.App.service;

import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.MedicalRecord;
import com.SafetyNetAlerts.App.model.Person;
import com.SafetyNetAlerts.App.repository.FireStRepository;
import com.SafetyNetAlerts.App.repository.MedicalRecRepository;
import com.SafetyNetAlerts.App.repository.PersonRepository;
import com.SafetyNetAlerts.App.service.dto.FireStationDto;
import com.SafetyNetAlerts.App.service.dto.FloodStationsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStService {

      private final PersonRepository personRepository;
      private final FireStRepository fireStRepository;
      private final MedicalRecRepository medicalRecRepository;
      private final PersonService personService;

      public FireStService(PersonRepository personRepository, FireStRepository fireStRepository, MedicalRecRepository medicalRecRepository, PersonService personService) {
            this.personRepository = personRepository;
            this.fireStRepository = fireStRepository;
            this.medicalRecRepository = medicalRecRepository;
            this.personService = personService;
      }

      public FireStationDto getFireStationDTO(String stationNumber) {

            List<FireStation> fireStationList = fireStRepository.findAllFireStationsByStationNumber(stationNumber);
            List<String> addressByStationNumber = fireStationList.stream().map(FireStation::getAddress).collect(Collectors.toList());
            List<FireStationDto.FireStationDtoPerson> fireStationDtoPersonList = new ArrayList<>();
            Integer adultesCount = 0;
            Integer childrenCount = 0;

            for (String fireStation : addressByStationNumber) {
                  List<Person> personListByStationAddress = personRepository.findAllPersonsByAddress(fireStation).stream().collect(Collectors.toList());

                  for (Person person : personListByStationAddress) {
                        MedicalRecord medicalRecord = medicalRecRepository.findMedicalRecordByFirstnameAndLastname(person.getFirstName(), person.getLastName());
                        String age = personService.calculateAge(medicalRecord.getBirthdate());
                        Integer ageInt = Integer.parseInt(age);
                        FireStationDto.FireStationDtoPerson fireStationDtoPerson = new FireStationDto.FireStationDtoPerson(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
                        fireStationDtoPersonList.add(fireStationDtoPerson);
                        if (ageInt <= 18) {
                              childrenCount++;
                        } else {
                              adultesCount++;
                        }
                  }
            }
            FireStationDto fireStationDto = new FireStationDto(adultesCount, childrenCount, fireStationDtoPersonList);
            return fireStationDto;
      }

      public List<FloodStationsDTO> getFloodStationsDTO(List<String> stationNumbers) {

            List<FloodStationsDTO> floodStationsDTOListToReturn = new ArrayList<>();

            for (String stationNumber : stationNumbers) {
                  List<FireStation> fireStationList = fireStRepository.findAllFireStationsByStationNumber(stationNumber);
                  List<String> addressByStationNumber = fireStationList.stream().map(FireStation::getAddress).collect(Collectors.toList());

                  for (String address : addressByStationNumber) {
                        List<FloodStationsDTO.FloodStationsDTOPersons> floodStationsDTOPersonsList = new ArrayList<>();

                        List<Person> personListByStationAddress = personRepository.findAllPersonsByAddress(address).stream().collect(Collectors.toList());

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
}
