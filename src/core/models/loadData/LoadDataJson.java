/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.loadData;

import core.models.Passenger;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Sahory Blanco
 */
public class LoadDataJson {
    public void loadFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                long id = obj.getLong("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                String birthDateStr = obj.getString("birthDate"); // formato "YYYY-MM-DD"
                int code = obj.getInt("countryPhoneCode");
                long phone = obj.getLong("phone");
                String country = obj.getString("country");

                LocalDate birthDate = LocalDate.parse(birthDateStr);

                Passenger p = new Passenger(id, firstname, lastname, birthDate, code, phone, country);
                //this.add(p); // método de tu repositorio
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
