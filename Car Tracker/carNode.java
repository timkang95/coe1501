/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cartracker;

/**
 *
 * @author Tim
 */
public class carNode {
    public String vin;
    public String make;
    public String model;
    public double price;
    public double mileage;
    public String color;
    
    public carNode(){
        
    }
  
    public carNode(String vin, String make, String model, double price, double mileage, String color){
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.color = color;
    }
           
    
    
}
