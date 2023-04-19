package com.SafetyNetAlerts.App.controller;

import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.repository.FireStRepository;
import com.SafetyNetAlerts.App.service.FireStService;
import com.SafetyNetAlerts.App.service.dto.FireStationDto;
import com.SafetyNetAlerts.App.service.dto.FloodStationsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStController {

    private final FireStService fireStService;
    private final FireStRepository fireStRepository;

    //Constructor
    public FireStController(FireStService fireStService, FireStRepository fireStRepository) {
        this.fireStService = fireStService;
        this.fireStRepository = fireStRepository;
    }

    //This URL returns a list of people from a single station number
    @RequestMapping(value = "firestation", method = RequestMethod.GET)
    public FireStationDto listPersonsByStationNumber(@RequestParam(name = "stationNumber")String stationNumber){
        return fireStService.getFireStationDTO(stationNumber);
    }

    //This URL returns a list of people from multiples station numbers
    @RequestMapping(value = "flood/stations", method = RequestMethod.GET)
    public List<FloodStationsDTO> listPersonsByManyStationNumbers(@RequestParam(name = "stations") List<String> stationNumbers){
        return fireStService.getFloodStationsDTO(stationNumbers);
    }

    //This URL returns a list every fire station
    @GetMapping("/fireStation")
    public List<FireStation> listOfAllFireStations(){return fireStRepository.findAllFireStations();}

    //This URL creates a new fire station
    @PostMapping("/fireStation")
    public ResponseEntity<FireStation> postNewFireStation(@RequestBody FireStation fireStation){
        ResponseEntity<FireStation> fireStationResponseEntity = fireStService.save(fireStation);
        return fireStationResponseEntity;
    }

    //This URL updates an existing fire station
    @PutMapping(value = "/fireStation")
    public void updateStationNumber(@RequestBody FireStation fireStation){
        fireStService.updateByStationNumber(fireStation);
    }

    //This URL deletes an existing fire station
    @DeleteMapping("/fireStation")
    public void deleteStation(@RequestBody FireStation fireStation){
        fireStService.deleteStation(fireStation);
    }
}
