package com.SafetyNetAlerts.App.analytics;

import com.SafetyNetAlerts.App.model.Data;
import com.SafetyNetAlerts.App.model.FireStations;
import com.SafetyNetAlerts.App.model.MedicalRecords;
import com.SafetyNetAlerts.App.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


@Component
public class ReadJsonFile {

      private final Data data;

      JSONParser jsonP = new JSONParser();



      public ReadJsonFile() throws IOException, ParseException {
            FileReader reader = new FileReader("C:\\Users\\Frank\\IdeaProjects\\SafetyNet Alerts\\App\\src\\main\\resources\\jsonList.json");
            Object dataJson = jsonP.parse(reader);
            JSONObject perObj = (JSONObject) dataJson;
            List<Person> persons = (JSONArray) perObj.get("persons");
            List<FireStations> fireStations = (JSONArray) perObj.get("firestations");
            List<MedicalRecords> medicalRecords = (JSONArray) perObj.get("medicalrecords");

            data = new Data();
            this.data.setPersons(persons);
            this.data.setFireStations(fireStations);
            this.data.setMedicalRecords(medicalRecords);
      }

}
