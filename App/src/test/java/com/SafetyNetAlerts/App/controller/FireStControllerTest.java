package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.service.dto.FireStationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class FireStControllerTest {
      @Autowired
      FireStController fireStController;
      @Autowired
      ReadJsonFile readJsonFile;

      @Test
      void listPersonsByStationNumber() {
            FireStationDto fireDTOListIntoString = fireStController.listPersonsByStationNumber("3");
            List<String> firstNameList = fireDTOListIntoString.getFireStationDTOpersonList().stream().map(f -> f.getFirstName()).collect(Collectors.toList());
            assertNotNull(firstNameList);
            assertThat(firstNameList).contains("Tenley");
      }

      @Test
      void listPersonsByManyStationNumbers() {
      }

      @Test
      void listOfAllFireStations() {
      }

      @Test
      void postNewFireStation() {
      }

      @Test
      void updateStationNumber() {
      }

      @Test
      void deleteStation() {
      }
}