package com.SafetyNetAlerts.App.analytics;

import com.SafetyNetAlerts.App.model.Data;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;


@Component
public class ReadJsonFile {


      JSONParser jsonP = new JSONParser();
      FileReader reader = new FileReader("C:\\Users\\Frank\\IdeaProjects\\SafetyNet Alerts\\App\\src\\main\\resources\\jsonList.json");
      Object dataJson = jsonP.parse(reader);
      Data data = new Data();


//      private static void parsePersonsObj(JSONObject persons) {
//            JSONObject personsObj = (JSONObject) persons.get("persons");
//            //Get persons firstName, lastName, address, city, zip, phone, email.
//
//            String fname = (String) personsObj.get("firstName");
//            String lname = (String) personsObj.get("lastName");
//            String add = (String) personsObj.get("address");
//            String city = (String) personsObj.get("city");
//            String zip = (String) personsObj.get("zip");
//            String phone = (String) personsObj.get("phone");
//            String email = (String) personsObj.get("email");
//
//      }


      public ReadJsonFile() throws IOException, ParseException {
      }
}
