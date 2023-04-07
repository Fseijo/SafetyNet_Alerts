package com.SafetyNetAlerts.App.analytics;

import com.SafetyNetAlerts.App.model.Data;
import com.SafetyNetAlerts.App.model.FireStation;
import com.SafetyNetAlerts.App.model.MedicalRecord;
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
      List<Person> people = new ArrayList<>();
      List<FireStation> fireStations = new ArrayList<>();
      List<MedicalRecord> medicalRecords = new ArrayList<>();


      public ReadJsonFile() throws IOException, ParseException {
            FileReader reader = new FileReader("C:\\Users\\Frank\\IdeaProjects\\SafetyNet Alerts\\App\\src\\main\\resources\\jsonList.json");
            Object dataJson = jsonP.parse(reader);
            JSONObject personObj = (JSONObject) dataJson;
            JSONArray personList = (JSONArray) personObj.get("persons");
            JSONArray firestationsList = (JSONArray) personObj.get("firestations");
            JSONArray medicalRecList = (JSONArray) personObj.get("medicalrecords");

            for (Object o : personList) {

                  JSONObject jsonObject = (JSONObject) o;
                  Person person = new Person(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString(), jsonObject.get("address").toString(), jsonObject.get("city").toString(), jsonObject.get("zip").toString(), jsonObject.get("phone").toString(), jsonObject.get("email").toString());
                  people.add(person);
            }

            for (Object o : firestationsList) {

                  JSONObject jsonObject = (JSONObject) o;
                  FireStation firesatation = new FireStation(jsonObject.get("address").toString(), jsonObject.get("station").toString());
                  fireStations.add(firesatation);
            }

            for (Object o : medicalRecList) {
                  //Loop in JsonObj
                  JSONObject jsonObject = (JSONObject) o;
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

                  MedicalRecord medicalRecord = new MedicalRecord(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString(), jsonObject.get("birthdate").toString(), medicationTab, allergiesTab);

                  medicalRecords.add(medicalRecord);
            }


            data = new Data();
            this.data.setPersons(people);
            this.data.setFireStations(fireStations);
            this.data.setMedicalRecords(medicalRecords);
      }

      public Data getData(){return data;}


}
