package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Aircraft extends Model {
    @Id
    private Long aircraft_ID;
    private String aircraft_number;
    private int capacity;

    @OneToMany
    private List<FlightSchedule> flight_schedule;

    public Aircraft (){
    }

    public Aircraft(Long id, String number, int capacity, List<FlightSchedule> flight_schedule){
        this.aircraft_ID = id;
        this.aircraft_number = number;
        this.capacity = capacity;
        this.flight_schedule = flight_schedule;
    }

    public static Finder<Long,Aircraft> find = new Finder<Long,Aircraft>(Aircraft.class);

    public static List<Aircraft> findAll(){
        return Aircraft.find.all();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();

        // Get all aircrafts from the DB and add to the options Hash map
        for(Aircraft a: Aircraft.findAll()) {
            options.put(Long.toString(a.getAircraftID()), a.getAircraftNumber());
        }
        return options;
    }

    public Long getAircraftID(){
        return aircraft_ID;
    }

    public String getAircraftNumber(){
        return aircraft_number;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setAircraftID(Long id){
        this.aircraft_ID = id;
    }

    public void setAircraftNumber(String number){
        this.aircraft_number = number;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    //get and set flight_schedule
    public List<FlightSchedule> getFlightSchedule(){
        return flight_schedule;
    }

    public void setFlightSchedule(List<FlightSchedule> flight_schedule){
        this.flight_schedule = flight_schedule;
    }

    public String toString(){
        String s = getAircraftNumber();
        return s;
    }

}