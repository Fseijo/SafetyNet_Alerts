package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.service.dto.FireStationDto;
import com.SafetyNetAlerts.App.service.dto.FloodStationsDTO;
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
            List<String> firstNameList = fireDTOListIntoString.getFireStationDTOpersonList().stream().map(FireStationDto.FireStationDtoPerson::getFirstName).collect(Collectors.toList());
            assertNotNull(firstNameList);
            assertThat(firstNameList).contains("Tenley");
      }

      @Test
      void listPersonsByManyStationNumbers() {
            List<String> stations = List.of("1", "2");
            List<FloodStationsDTO> floodStationsDTOList = fireStController.listPersonsByManyStationNumbers(stations);
            List<String> addressList = floodStationsDTOList.stream().map(FloodStationsDTO::getAddress).collect(Collectors.toList());
            assertNotNull(floodStationsDTOList);
            assertThat(addressList).contains("644 Gershwin Cir");
      }

      @Test
      void listOfAllFireStations() {
            List<String> fireStationsAddressList = fireStController.listOfAllFireStations().stream().map(FireStation::getAddress).collect(Collectors.toList());
            assertNotNull(fireStationsAddressList);
            assertThat(fireStationsAddressList).contains("834 Binoc Ave");
      }

      @Test
      void postNewFireStation() {
            FireStation newFireStation = new FireStation("8 Av. Violette", "6");
            fireStController.postNewFireStation(newFireStation);
            FireStation fireStation = readJsonFile.getData().getFireStations().stream().filter(f-> f.getAddress().equals(newFireStation.getAddress())).findFirst().get();
            assertThat(fireStation.getAddress()).isEqualTo(newFireStation.getAddress());
      }

      @Test
      void updateStationNumber() {
            FireStation newFireStation = new FireStation("8 Av. Violette", "6");
            fireStController.postNewFireStation(newFireStation);
            FireStation updateFireStation = new FireStation("8 Av. Violette", "10");
            fireStController.updateStationNumber(updateFireStation);

            FireStation fireStation = readJsonFile.getData().getFireStations().stream()
                    .filter(f-> f.getAddress().equals(updateFireStation.getAddress()) && (f.getStation().equals(updateFireStation.getStation())))
                    .findAny().get();
            assertThat(fireStation.getAddress()).isEqualTo(updateFireStation.getAddress());
      }

      @Test
      void deleteStation() {
            FireStation newFireStation = new FireStation("8 Av. Violette", "6");
            fireStController.postNewFireStation(newFireStation);
            fireStController.deleteStation(newFireStation);
            List<FireStation> fireStationList = readJsonFile.getData().getFireStations();

            assertEquals(13,fireStationList.size());
      }
}