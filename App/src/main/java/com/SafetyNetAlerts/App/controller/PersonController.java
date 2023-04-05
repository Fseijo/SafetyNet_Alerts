package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.model.Person;

import com.SafetyNetAlerts.App.service.PersonService;
import org.springframework.stereotype.Controller;
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

//      @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
//      public List<String> listPhoneNumberByFirestation(@RequestParam(name = "firestationToFind")String firestationToFind){
//            return personService.findAllPhoneNumbersByFireStation(firestationToFind);
//      }
      @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
      public List<String> listPhoneNumberByFirestationSteam(@RequestParam(name = "station")String station){
            return personService.findAllPhoneByFireStation(station);
      }



//      @GetMapping("/person")
//      public List<Person> listePersons(){
//            return personService.findAllPersons();
//      }

}
