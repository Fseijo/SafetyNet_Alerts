package com.SafetyNetAlerts.App.service;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.Data;
import com.SafetyNetAlerts.App.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonsService {

      public PersonsService(ReadJsonFile readJsonFile) {
            this.readJsonFile = readJsonFile;
      }

      private final ReadJsonFile readJsonFile;


}
