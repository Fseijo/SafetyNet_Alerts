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

    public FireStController(FireStService fireStService, FireStRepository fireStRepository) {
        this.fireStService = fireStService;
        this.fireStRepository = fireStRepository;
    }

    @RequestMapping(value = "firestation", method = RequestMethod.GET)
    public FireStationDto listPersonsByStationNumber(@RequestParam(name = "stationNumber")String stationNumber){
        return fireStService.getFireStationDTO(stationNumber);
    }

    @RequestMapping(value = "flood/stations", method = RequestMethod.GET)
    public List<FloodStationsDTO> listPersonsByManyStationNumbers(@RequestParam(name = "stations") List<String> stationNumbers){
        return fireStService.getFloodStationsDTO(stationNumbers);
    }

    @GetMapping("/fireStation")
    public List<FireStation> listOfAllFireStations(){return fireStRepository.findAllFireStations();}

    @PostMapping("/fireStation")
    public ResponseEntity<FireStation> postNewFireStation(@RequestBody FireStation fireStation){
        ResponseEntity<FireStation> fireStationResponseEntity = fireStService.save(fireStation);
        return fireStationResponseEntity;
    }

    @PutMapping(value = "/fireStation")
    public void updateStationNumber(@RequestBody FireStation fireStation){
        fireStService.updateByStationNumber(fireStation);
    }

    @DeleteMapping("/fireStation")
    public void deleteStation(@RequestBody FireStation fireStation){
        fireStService.deleteStation(fireStation);
    }
}
