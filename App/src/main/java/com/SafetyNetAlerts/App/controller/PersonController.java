package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.model.Person;
import com.SafetyNetAlerts.App.repository.PersonRepository;
import com.SafetyNetAlerts.App.service.dto.ChildAlertDTO;
import com.SafetyNetAlerts.App.service.dto.FireDTO;
import com.SafetyNetAlerts.App.service.PersonService;
import com.SafetyNetAlerts.App.service.dto.PersonInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

      private final PersonService personService;
      private final PersonRepository personRepository;


      public PersonController(PersonService personService, PersonRepository personRepository){this.personService = personService;
            this.personRepository = personRepository;
      }

      @RequestMapping(value = "communityEmail", method = RequestMethod.GET)
      public List<String> listEmails(@RequestParam(name = "city")String city){
            return personService.findAllEmailsByCity(city);
      }

      @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
      public List<String> listPhoneNumberByFirestation(@RequestParam(name = "fireStationToFind")String fireStationToFind){
            return personService.findAllPhoneNumbersByFireStation(fireStationToFind);
      }

      @RequestMapping(value = "fire", method = RequestMethod.GET)
      public List<FireDTO> listPeopleByAddress(@RequestParam(name = "addressToFind") String addressToFind){
            return personService.getListFireDTO(addressToFind);
      }

      @RequestMapping(value = "childAlert", method = RequestMethod.GET)
      public List<ChildAlertDTO> listChildAlertByAddress(@RequestParam(name = "address") String address){
            return personService.getListChildAlertDTO(address);
      }

      @RequestMapping(value = "personInfo", method = RequestMethod.GET)
      public List<PersonInfoDTO> listInformationsByPerson(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName){
          return personService.getListPersonInfoDTO(firstName, lastName);
      }

      @GetMapping("/person")
      public List<Person> listOfAllPersons(){
            return personRepository.findAllPersons();
      }

      @PostMapping(value = "/person/addNewPerson")
      public Person postNewPerson(@RequestBody Person person){
            return personRepository.saveNewPerson(person);
      }
}
