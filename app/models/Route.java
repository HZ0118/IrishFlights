package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Route extends Model {
    @Id
    private Long route_ID;
    private String route_code;
    private String airport;
    private String destination;

    @OneToMany
    private List<FlightSchedule> flight_schedule;

    public Route(){
    }

    public Route(Long id, String code, String airport, String destination, List<FlightSchedule> flight_schedule){
        this.route_ID = id;
        this.route_code = code;
        this.airport = airport;
        this.destination = destination;
        this.flight_schedule = flight_schedule;
    }

    public static Finder<Long, Route> find = new Finder<Long, Route>(Route.class);

    public static List<Route> findAll(){

        return Route.find.all();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();

        // Get all categories from the DB and add to the options Hash map
        for(Route r: Route.findAll()) {
            options.put(r.getRouteID().toString(), r.getRouteCode());
        }
        return options;
    }

    //get and set route id
    public Long getRouteID(){

        return route_ID;
    }

    public void setRouteID(Long id){

        this.route_ID = id;
    }

    //get and set route code
    public String getRouteCode(){
        return route_code;
    }

    public void setRouteCode(String code){
        this.route_code = code;
    }

    //get and set airport
    public String getAirport(){
        return airport;
    }

    public void setAirport(String airport){
        this.airport = airport;
    }

    //Get and set destination
    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    //get and set flight_schedule
    public List<FlightSchedule> getFlightSchedule(){
        return flight_schedule;
    }

    public void setFlightSchedule(List<FlightSchedule> flight_schedule){
        this.flight_schedule = flight_schedule;
    }

    public String toString() {
        String s = getAirport() + " -> " + getDestination();
        return s;
    }

}