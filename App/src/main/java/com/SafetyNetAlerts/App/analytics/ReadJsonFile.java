package com.SafetyNetAlerts.App.analytics;

import com.SafetyNetAlerts.App.model.Data;
import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.MedicalRecords;
import com.SafetyNetAlerts.App.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class ReadJsonFile {

      private final Data data;

      JSONParser jsonP = new JSONParser();
      List<Person> people = new ArrayList<Person>();
      List<FireStation> fireStations = new ArrayList<FireStation>();
      List<MedicalRecords> medicalRecords = new ArrayList<MedicalRecords>();


      public ReadJsonFile() throws IOException, ParseException {
            FileReader reader = new FileReader("C:\\Users\\Frank\\IdeaProjects\\SafetyNet Alerts\\App\\src\\main\\resources\\jsonList.json");
            Object dataJson = jsonP.parse(reader);
            JSONObject personObj = (JSONObject) dataJson;
            JSONArray personList = (JSONArray) personObj.get("persons");
            JSONArray firestationsList = (JSONArray) personObj.get("firestations");
            JSONArray medicalRecList = (JSONArray) personObj.get("medicalrecords");

            for (int k = 0; k < personList.size(); k++) {

                  JSONObject jsonObject = (JSONObject) personList.get(k);
                  Person person = new Person(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString(), jsonObject.get("address").toString(), jsonObject.get("city").toString(), jsonObject.get("zip").toString(), jsonObject.get("phone").toString(), jsonObject.get("email").toString());
                  people.add(person);
            }

            for (int k = 0; k < firestationsList.size(); k++) {

                  JSONObject jsonObject = (JSONObject) firestationsList.get(k);
                  FireStation firesatation = new FireStation(jsonObject.get("address").toString(), jsonObject.get("station").toString());
                  fireStations.add(firesatation);
            }

            for (int k = 0; k < medicalRecList.size(); k++) {
                  //Loop in JsonObj
                  JSONObject jsonObject = (JSONObject) medicalRecList.get(k);
                  //Get medications elements
                  JSONArray jsonArrayMedications = (JSONArray) jsonObject.get("medications");
                  String[] medicationTab = new String[jsonArrayMedications.size()];
                  //Get allergies elements
                  JSONArray jsonArrayAllergies = (JSONArray) jsonObject.get("allergies");
                  String[] allergiesTab = new String[jsonArrayAllergies.size()];
                  //Loop in JArray to add elements into medicationTab
                  for (int i = 0; i < jsonArrayMedications.size(); i++) {
                        medicationTab[i] = (jsonArrayMedications.get(i).toString());
                  }
                  //Loop in JArray to add elements into allergiesTab
                  for (int i = 0; i < jsonArrayAllergies.size(); i++) {
                        allergiesTab[i] = (jsonArrayAllergies.get(i).toString());
                  }

                  MedicalRecords medicalRecord = new MedicalRecords(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString(), jsonObject.get("birthdate").toString(), medicationTab, allergiesTab);

                  medicalRecords.add(medicalRecord);
            }


            data = new Data();
            this.data.setPersons(people);
            this.data.setFireStations(fireStations);
            this.data.setMedicalRecords(medicalRecords);
      }

      public Data getData(){return data;}


}
