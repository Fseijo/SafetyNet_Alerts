package com.SafetyNetAlerts.App.service;

import com.SafetyNetAlerts.App.repository.PersonRepository;
import org.springframework.stereotype.Service;


@Service
public class MedicalRecService {

      private final PersonRepository personRepository;

      public MedicalRecService(PersonRepository personRepository) {
            this.personRepository = personRepository;
      }



}
