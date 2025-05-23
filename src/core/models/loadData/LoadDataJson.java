/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.loadData;

import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PassengerRepository;
import core.models.storage.PlaneRepository;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Sahory Blanco
 */
public class LoadDataJson {
 
    public void loadAllData() {
        loadLocations("json/locations.json"); 
        loadPlanes("json/planes.json");       
        loadPassengers("json/passengers.json"); 
        loadFlights("json/flights.json");     
    }

    public void loadLocations(String filename) {
        LocationRepository locationRepo = LocationRepository.getInstance();
        try (FileReader reader = new FileReader(filename)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String airportId = obj.getString("airportId");
                String airportName = obj.getString("airportName");
                String airportCity = obj.getString("airportCity");
                String airportCountry = obj.getString("airportCountry");
                double airportLatitude = obj.getDouble("airportLatitude");
                double airportLongitude = obj.getDouble("airportLongitude");

                Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
                locationRepo.addLocation(location);
            }
            System.out.println("Locations loaded successfully: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading locations file (" + filename + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing locations JSON (" + filename + "): " + e.getMessage());
        }
    }

    public void loadPlanes(String filename) {
        PlaneRepository planeRepo = PlaneRepository.getInstance();
        try (FileReader reader = new FileReader(filename)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");

                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                if (planeRepo != null) {
                    planeRepo.addPlane(plane);
                } else {
                    System.err.println("PlaneRepository is null. Cannot add plane.");
                }
            }
            System.out.println("Planes loaded successfully: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading planes file (" + filename + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing planes JSON (" + filename + "): " + e.getMessage());
        }
    }

    public void loadPassengers(String filename) {
        PassengerRepository passengerRepo = PassengerRepository.getInstance();
        try (FileReader reader = new FileReader(filename)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                long id = obj.getLong("id");
                String firstname = obj.getString("firstname");
                String lastname = obj.getString("lastname");
                String birthDateStr = obj.getString("birthDate");
                int code = obj.getInt("countryPhoneCode");
                long phone = obj.getLong("phone");
                String country = obj.getString("country");

                LocalDate birthDate = LocalDate.parse(birthDateStr);

                Passenger p = new Passenger(id, firstname, lastname, birthDate, code, phone, country);
                passengerRepo.addPassenger(p);
            }
            System.out.println("Passengers loaded successfully: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading passengers file (" + filename + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing passengers JSON (" + filename + "): " + e.getMessage());
        }
    }

    public void loadFlights(String filename) {
        FlightRepository flightRepo = FlightRepository.getInstance();
        PlaneRepository planeRepo = PlaneRepository.getInstance();
        LocationRepository locationRepo = LocationRepository.getInstance();

        try (FileReader reader = new FileReader(filename)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String planeId = obj.getString("plane");
                String departureLocationId = obj.getString("departureLocation");
                String arrivalLocationId = obj.getString("arrivalLocation");
                String scaleLocationId = obj.isNull("scaleLocation") ? null : obj.getString("scaleLocation");
                String departureDateStr = obj.getString("departureDate");

                int hoursDurationArrival = obj.getInt("hoursDurationArrival");
                int minutesDurationArrival = obj.getInt("minutesDurationArrival");
                int hoursDurationScale = obj.getInt("hoursDurationScale");
                int minutesDurationScale = obj.getInt("minutesDurationScale");

                Plane plane = (planeRepo != null) ? planeRepo.getPlane(planeId) : null;
                Location departureLocation = locationRepo.getLocation(departureLocationId);
                Location arrivalLocation = locationRepo.getLocation(arrivalLocationId);
                Location scaleLocation = null;
                if (scaleLocationId != null) {
                    scaleLocation = locationRepo.getLocation(scaleLocationId);
                }

                LocalDateTime departureDate = LocalDateTime.parse(departureDateStr);

                Flight flight;
                if (plane == null || departureLocation == null || arrivalLocation == null) {
                    System.err.println("Skipping flight " + id + " due to missing plane or location references. Plane: " + planeId + ", Departure: " + departureLocationId + ", Arrival: " + arrivalLocationId);
                    continue;
                }
                if (scaleLocationId != null && scaleLocation == null) {
                    System.err.println("Skipping flight " + id + " due to missing scale location reference: " + scaleLocationId);
                    continue;
                }

                if (scaleLocation != null) {
                    flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate,
                                        hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
                } else {
                    flight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate,
                                        hoursDurationArrival, minutesDurationArrival);
                }
                flightRepo.addFlight(flight);
            }
            System.out.println("Flights loaded successfully: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading flights file (" + filename + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing flights JSON (" + filename + "): " + e.getMessage() + ". Check data consistency.");
            e.printStackTrace();
        }
    }
}
