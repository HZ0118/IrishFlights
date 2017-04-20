package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;
import models.users.Customer;

@Entity
public class Transaction extends Model {
    private int booking_ID;
    private String booking_date;
    private String departure_date;
    private String ticket_type;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private FlightSchedule flight_schedule;

    public Transaction(){

    }

    public Transaction(int id, String booking_date, String departure_date, String ticket_type, Customer customer, FlightSchedule flight_schedule){
        this.booking_ID = id;
        this.booking_date = booking_date;
        this.departure_date = departure_date;
        this.ticket_type = ticket_type;
        this.customer = customer;
        this.flight_schedule = flight_schedule;
    }

    public static Finder<Integer, Transaction> find = new Finder<Integer, Transaction>(Transaction.class);

    public static List<Transaction> findAll() {
        return Transaction.find.all();
    }

    //get and set booking_ID
    public int getBookingID(){
        return booking_ID;
    }

    public void setBookingID(int id){
        this.booking_ID = id;
    }

    //get and set booking_date
    public String getBookingDate(){
        return booking_date;
    }

    public void setBookingDate(String booking_date){
        this.booking_date = booking_date;
    }

    //get and set departure_date
    public String getDepDate(){
        return departure_date;
    }

    public void setDepDate(String departure_date){
        this.departure_date = departure_date;
    }

    //get and set ticket_type
    public String getTicketType(){
        return ticket_type;
    }

    public void setTicketType(String ticket_type){
        this.ticket_type = ticket_type;
    }

    //get and set customer
    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    //get and set flight_schedule
    public FlightSchedule getFlightSchedule(){
        return flight_schedule;
    }

    public void setFlightSchedule(FlightSchedule flight_schedule){
        this.flight_schedule = flight_schedule;
    }

}