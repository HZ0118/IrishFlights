package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class FlightSchedule extends Model {
    @Id
    @Constraints.Required
    private int flight_ID;
    @Constraints.Required
    private String flight_date;
    @Constraints.Required
    private String departure_time;
    @Constraints.Required
    private String arrival_time;
    private double price;

    @ManyToOne
    private Aircraft aircraft;

    @ManyToOne
    private Route routes;

    public FlightSchedule(){

    }

    public FlightSchedule(int id, String flight_date, String departure_time, String arrival_time, double cost, Aircraft aircraft, Route routes){
        this.flight_ID = id;
        this.flight_date = flight_date;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.price = cost;
        this.aircraft = aircraft;
        this.routes = routes;
    }

    public static Finder<Integer, FlightSchedule> find = new Finder<Integer,FlightSchedule>(FlightSchedule.class);

    public static List<FlightSchedule> findAll(){
        return FlightSchedule.find.all();
    }

    //get and set flight id
    public int getFlightID(){
        return flight_ID;
    }

    public void setFlightID(int id){
        this.flight_ID = id;
    }

    //get and set flight date
    public String getFlightDate(){
        return flight_date;
    }

    public void setFlightDate(String flight_date){
        this.flight_date = flight_date;
    }

    //get and set departure_time
    public String getDepTime(){
        return departure_time;
    }

    public void setDepTime(String departure_time){
        this.departure_time = departure_time;
    }

    //Get and set arrival_time
    public String getArrTime(){
        return arrival_time;
    }

    public void setArrTime(String arrival_time){
        this.arrival_time = arrival_time;
    }

    //get and set cost
    public double getPrice(){
        return price;
    }

    public void setPrice(double cost){
        this.price = cost;
    }

    //get and set aircraft
    public Aircraft getAircraft(){
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft){
        this.aircraft = aircraft;
    }

    //get and set routes
    public Route getRoutes(){
        return routes;
    }

    public void setRoutes(Route routes){
        this.routes = routes;
    }

}