package com.SafetyNetAlerts.App.repository;


import com.SafetyNetAlerts.App.analytics.ReadJsonFile;
import com.SafetyNetAlerts.App.model.FireStation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FireStRepository {

      private final ReadJsonFile readJsonFile;

      public FireStRepository(ReadJsonFile readJsonFile) {
            this.readJsonFile = readJsonFile;
      }

      public List<FireStation> findAllFireStations() {
            return readJsonFile.getData().getFireStations();
      }

      public List<FireStation> findAllFireStationsByAddress(String address) {
           return readJsonFile.getData().getFireStations().stream()
                    .filter(fireStation -> fireStation.getAddress().equals(address))
                    .collect(Collectors.toList());
      }

}
