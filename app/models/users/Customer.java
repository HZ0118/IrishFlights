package models.users;

import models.booking.Basket;
import models.booking.ShopOrder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity

@DiscriminatorValue("customer")
public class Customer extends User {

    private String name;
    private String address;
    private String mobile;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Basket basket;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ShopOrder> orders;

    @OneToMany
    private List<models.Transaction> transaction;

    public Customer(){

    }

    public Customer(String email, String name, String password, String address, String mobile, List<models.Transaction> transaction){
        super(email,name,password);
        this.address = address;
        this.mobile = mobile;
        this.transaction = transaction;
    }

    //get and set name
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    //get and set address
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }


    //get and set mobile
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    //get and set basket
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    //get and set order
    public List<ShopOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ShopOrder> orders) {
        this.orders = orders;
    }

    //get and set transaction
    public List<models.Transaction> getTransaction(){
        return transaction;
    }

    public void setTransaction(List<models.Transaction> transaction){
        this.transaction = transaction;
    }

}