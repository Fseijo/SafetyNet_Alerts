package com.SafetyNetAlerts.App.repository;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

      private final ReadJsonFile readJsonFile;

      public PersonRepository(ReadJsonFile readJsonFile){
            this.readJsonFile = readJsonFile;
      }

      public List<Person> findAllPersons(){
            return readJsonFile.getData().getPersons();
      }

      public List<Person> findAllPersonsByAddress(String address){
            return readJsonFile.getData().getPersons().stream().filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());
      }


}
