package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.service.dto.FireDTO;
import com.SafetyNetAlerts.App.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

      private final PersonService personService;

      public PersonController(PersonService personService){this.personService = personService;}

      @RequestMapping(value = "communityEmail", method = RequestMethod.GET)
      public List<String> listEmails(@RequestParam(name = "city")String city){
            return personService.findAllEmailsByCity(city);
      }

      @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
      public List<String> listPhoneNumberByFirestation(@RequestParam(name = "firestationToFind")String firestationToFind){
            return personService.findAllPhoneNumbersByFireStation(firestationToFind);
      }

      @RequestMapping(value = "fire", method = RequestMethod.GET)
      public List<FireDTO> listPeopleByAddress(@RequestParam(name = "addressToFind") String addressToFind){
            return personService.getListFireDTO(addressToFind);
      }
}
