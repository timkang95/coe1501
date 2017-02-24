/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package cartracker;


import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * @author Tim
 * meant to order cars and their information in pqs
 */
public class CarTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //variables are here
        boolean menu = true;
        boolean found;
        Scanner user_input = new Scanner( System.in );
        String userinput;
        String userChange;
        String vin;
        String make;
        String model;
        double price;
        double mileage;
        String color; 
        int index = 0;
        int placeholder = 0;
        carNode temp = new carNode(null,null,null,0,0,null);
        
        //hashmap to hold the nodes from the index numbers of the 2 pqs arranged in mileage and price
        HashMap<Integer, carNode> hmap = new HashMap<>();
        indexMinPQ<Double> mileagePQ = new indexMinPQ<>(100);
        indexMinPQ<Double> pricePQ = new indexMinPQ<>(100);
         
        
        //menu interface
        while(menu){
            System.out.println("\tMenu");
            System.out.println("---------------------");
            System.out.println("Press 1 to add a car to your list");
            System.out.println("Press 2 to update a car listing");
            System.out.println("Press 3 to remove a car from your list");
            System.out.println("Press 4 to retrieve the lowest price car");
            System.out.println("Press 5 to retrieve the lowest mileage car");
            System.out.println("Press 6 to retrieve the lowest price of a make and model car");
            System.out.println("Press 7 to retrieve the lowest mileage of a make and model car");
            System.out.println("Press 8 to see the whole list of cars according to price");
            System.out.println("Press 9 to see the whole list of cars according to mileage");
            System.out.println("Press q to quit");
            
            userinput = user_input.next();
            
            //problem check
            while(!userinput.equals("1") && !userinput.equals("2") && !userinput.equals("3") && !userinput.equals("4") && !userinput.equals("5") && !userinput.equals("6") && !userinput.equals("7") && !userinput.equals("8") && !userinput.equals("9") && !userinput.equals("q")){
                System.out.println("Invalid input");
                System.out.println("\tMenu");
                System.out.println("---------------------");
                System.out.println("Press 1 to add a car to your list");
                System.out.println("Press 2 to update a car listing");
                System.out.println("Press 3 to remove a car from your list");
                System.out.println("Press 4 to retrieve the lowest price car");
                System.out.println("Press 5 to retrieve the lowest mileage car");
                System.out.println("Press 6 to retrieve the lowest price of a make and model car");
                System.out.println("Press 7 to retrieve the lowest mileage of a make and model car");
                System.out.println("Press 8 to see the whole list of cars according to price");
                System.out.println("Press 9 to see the whole list of cars according to mileage");
                System.out.println("Press q to quit");

                userinput = user_input.next();
             }  
             
             
             //allows the assignment of cars
             if(userinput.equals("1")){
                 System.out.println("Please enter the VIN number of the car");
                 vin = user_input.next();
                 for(int i = 0; i < vin.length(); i++){
                     if(vin.charAt(i) == 'O' || vin.charAt(i) == 'Q' || vin.charAt(i) == 'I'){
                         vin = " ";
                         break;
                     }
                 }
                 
                 while(vin.length() != 17){
                     System.out.println("Please enter a valid VIN number \n\t Only 17 Characters \n\t No capital i, o or q");
                     System.out.println("Please enter the VIN number of the car");
                     vin = user_input.next();
                     
                     for(int i = 0; i < vin.length(); i++){
                        if(vin.charAt(i) == 'O' || vin.charAt(i) == 'Q' || vin.charAt(i) == 'I'){
                            vin = " ";
                            break;
                        }
                     }
                 } 
                 System.out.println("Please enter the make of the car");
                 make = user_input.next();
                 
                 System.out.println("Please enter the model of the car");
                 model = user_input.next();
                 
                 System.out.println("Please enter the price of the car (in dollars)");
                 price = Double.parseDouble(user_input.next());
                 
                 System.out.println("Please enter the mileage of the car");
                 mileage = Double.parseDouble(user_input.next());
                 
                 System.out.println("Please enter the color of the car");
                 color = user_input.next();
                 
                 carNode newNode = new carNode(vin, make, model, price, mileage, color);
                 
                 mileagePQ.insert(index, newNode.mileage);
                 pricePQ.insert(index, newNode.price);
                 
                 hmap.put(index, newNode);
                 
                 index++;
                 
                 
                 
                 
             }
             //allows user to update an already inputted car
             if(userinput.equals("2")){
                 System.out.println("What is the VIN number of the car you would like to change?");
                 vin = user_input.next();
                 found = false;
                 
                 for (int i : mileagePQ) {
                     temp = hmap.get(i);
                     if(temp.vin.equals(vin)){
                         placeholder = i;
                         found=true;
                         break;
                     }
                 }
                 
                 //makes sure it exists
                 while(!found){
                     System.out.println("VIN number was not found");
                     System.out.println("What is the VIN number of the car you would like to change?");
                     vin = user_input.next();
                     for (int i : mileagePQ) {
                        temp = hmap.get(i);
                        if(temp.vin.equals(vin)){
                            placeholder = i;
                            found=true;
                            break;
                        }
                     }
                 }
                 
                 System.out.println("What would you like to change for " + vin);
                 System.out.println("Type 1 for price\nType 2 for mileage\nType 3 for color");
                 userChange = user_input.next();
                 //user options of change
                 while(!userChange.equals("1")&& !userChange.equals("2")&& !userChange.equals("3")){
                     System.out.println("Invalid option");
                     System.out.println("What would you like to change for " + vin);
                     System.out.println("Type 1 for price\nType 2 for mileage\nType 3 for color");
                     userChange = user_input.next();
                 }
                 
                 if(userChange.equals("1")){
                     System.out.println("Please enter the new price of the vehicle (in dollars)");
                     price = Double.parseDouble(user_input.next());
                     
                     temp.price = price;
                     pricePQ.delete(placeholder);
                     pricePQ.insert(placeholder, price);
                     hmap.replace(placeholder, temp);
                 }
                 
                 if(userChange.equals("2")){
                     System.out.println("Please enter the new mileage of the vehicle");
                     mileage = Double.parseDouble(user_input.next());
                     
                     temp.mileage = mileage;
                     mileagePQ.delete(placeholder);
                     mileagePQ.insert(placeholder, mileage);
                     hmap.replace(placeholder, temp);
                 }
                 
                 if(userChange.equals("3")){
                     System.out.println("Please enter the new color of the vehicle");
                     color = user_input.next();
                     
                     temp.color = color;
                     hmap.replace(placeholder, temp);
                 }
                 
                 
                 
                 
             }
             
             //removal of a certain car
             if(userinput.equals("3")){
                 System.out.println("What is the VIN number of the car you would like to remove?");
                 vin = user_input.next();
                 for (int i : mileagePQ) {
                     temp = hmap.get(i);
                     if(temp.vin.equals(vin)){
                         mileagePQ.delete(i);
                         pricePQ.delete(i);
                         hmap.remove(i);
                     }
                 }
             }
             //retrieval of lowest price or mileage
             if(userinput.equals("4")){
                 System.out.println("Retrieveing the lowest priced car in your list");
                 placeholder = pricePQ.minIndex();
                 temp = hmap.get(placeholder);
                 
                 System.out.println("The lowest priced car: ");
                 System.out.println("\tVIN number " + temp.vin);
                 System.out.println("\tMake: " + temp.make);
                 System.out.println("\tModel: " + temp.model);
                 System.out.println("\tPrice: $" + temp.price);
                 System.out.println("\tMileage: " + temp.mileage + "miles");
                 System.out.println("\tColor: " + temp.color);
                 
             }
             
             if(userinput.equals("5")){
                 System.out.println("Retrieveing the lowest mileage car in your list");
                 placeholder = pricePQ.minIndex();
                 temp = hmap.get(placeholder);
                 
                 System.out.println("The lowest mileage car: ");
                 System.out.println("\tVIN number " + temp.vin);
                 System.out.println("\tMake: " + temp.make);
                 System.out.println("\tModel: " + temp.model);
                 System.out.println("\tPrice: $" + temp.price);
                 System.out.println("\tMileage: " + temp.mileage + "miles");
                 System.out.println("\tColor: " + temp.color);
             }
             //retrieval of lowest price or mileage with given parameters
             if(userinput.equals("6")){
                 System.out.println("Retrieveing the lowest priced car in your list");
                 System.out.println("Please enter the make of the vehicle");
                 make = user_input.next();
                 System.out.println("Please enter the model of the vehicle");
                 model = user_input.next();
                 found = false;
                 for (int i : pricePQ) {
                     temp = hmap.get(i);
                     if(temp.model.equals(model) && temp.make.equals(make)){
                         System.out.println("The lowest priced car with " + make + " " + model);
                         System.out.println("\tVIN number " + temp.vin);
                         System.out.println("\tMake: " + temp.make);
                         System.out.println("\tModel: " + temp.model);
                         System.out.println("\tPrice: $" + temp.price);
                         System.out.println("\tMileage: " + temp.mileage + "miles");
                         System.out.println("\tColor: " + temp.color);
                         found = true;
                         break;
                     }
                 }
                 if(found == false){
                     System.out.println("Match of make and model not found");
                 }
             }
             
             if(userinput.equals("7")){
                 System.out.println("Retrieveing the lowest mileage car in your list");
                 System.out.println("Please enter the make of the vehicle");
                 make = user_input.next();
                 System.out.println("Please enter the model of the vehicle");
                 model = user_input.next();
                 found = false;
                 for (int i : mileagePQ) {
                     temp = hmap.get(i);
                     if(temp.model.equals(model) && temp.make.equals(make)){
                         System.out.println("The lowest mileage car with " + make + " " + model);
                         System.out.println("\tVIN number " + temp.vin);
                         System.out.println("\tMake: " + temp.make);
                         System.out.println("\tModel: " + temp.model);
                         System.out.println("\tPrice: $" + temp.price);
                         System.out.println("\tMileage: " + temp.mileage + "miles");
                         System.out.println("\tColor: " + temp.color);
                         found = true;
                         break;
                     }
                 }
                 if(found == false){
                     System.out.println("Match of make and model not found");
                 }
             }
             //list for user preference
             if(userinput.equals("8")){
                 System.out.println("List of all cars according to price");
                 for (int i : pricePQ) {
                     temp = hmap.get(i);
                     
                         System.out.println("");
                         System.out.println("\tVIN number " + temp.vin);
                         System.out.println("\tMake: " + temp.make);
                         System.out.println("\tModel: " + temp.model);
                         System.out.println("\tPrice: $" + temp.price);
                         System.out.println("\tMileage: " + temp.mileage + " miles");
                         System.out.println("\tColor: " + temp.color);
                         
                     
                 }
             }
             
             if(userinput.equals("9")){
                 System.out.println("List of all cars according to mileage");
                 for (int i : mileagePQ) {
                     temp = hmap.get(i);
                     
                         System.out.println("");
                         System.out.println("\tVIN number " + temp.vin);
                         System.out.println("\tMake: " + temp.make);
                         System.out.println("\tModel: " + temp.model);
                         System.out.println("\tPrice: $" + temp.price);
                         System.out.println("\tMileage: " + temp.mileage + " miles");
                         System.out.println("\tColor: " + temp.color);
                         
                     
                 }
             }
             //quit function
             if(userinput.equals("q")){
                 menu = false;
                 
             }
        }
    }
    
}
