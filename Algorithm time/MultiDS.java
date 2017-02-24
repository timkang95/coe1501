/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Random;

/**
 *
 * @author Timothy Kang
 */
public class MultiDS<T> implements PrimQ<T>, Reorder{
    
    private T[] deck;
    private int freeSpace = 0;
    private int fit;
    
    public MultiDS(int size){
        deck = (T[]) new Object[size];
        fit = size;
    }
    
    @Override
    public boolean addItem(T item) {
        if(full()){
            return false;
        }
        if(empty()){
            freeSpace = 0;
        }
        deck[freeSpace] = item;
        freeSpace++;
        return true;
    }

    @Override
    public T removeItem() {
        T card = deck[0];
        deck[0] = null;
        
        for(int i = 0; i <freeSpace - 1; i++){
            deck[i] = deck[i+1];
        }
        freeSpace--;
        return card;
    } 

    @Override
    public boolean full() {
        if(freeSpace == fit){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean empty() {
       if(freeSpace <= 0){
           return true;
       }
       else 
           return false;
    }

    @Override
    public int size() {
        int openSpaces = freeSpace;
        return openSpaces;
    }

    @Override
    public void clear() {
        for(int i = 0; i < fit; i++){
            deck[i] = null;
        }
        freeSpace = 0;
    }
    
    public String toString(){
        String contents = new String();
        contents = "Contents: \n";
        for(int i = 0; i<freeSpace; i++){
            contents += deck[i] + " ";
        }
        return contents;
    }
    @Override
    public void reverse() {
        T temp[] = (T[]) new Object[fit];
        for(int i = 0; i < freeSpace; i++){
            temp[i] = deck[freeSpace - 1 - i];
        }
        for(int i = 0; i < freeSpace; i++){
            deck[i] = temp[i];
        }
    }

    @Override
    public void shiftRight() {
        T temp = deck[freeSpace-1];
        for(int i = 0; i <freeSpace; i++){
            deck[freeSpace-i] = deck[freeSpace-1-i];
        }
        deck[0] = temp;
    }

    @Override
    public void shiftLeft() {
        T temp = deck[0];
        for(int i = 0; i <freeSpace; i++){
            deck[i] = deck[i+1];
        }
        deck[freeSpace-1] = temp;
    }

    @Override
    public void shuffle() {
        Random spot = new Random();
        
        T temp[] = (T[]) new Object[fit];
        for(int i = 0; i < freeSpace; i++){
            temp[i] = deck[i];
        }
        
        for(int i = 0; i<freeSpace; i++){
            int randSpot = spot.nextInt(freeSpace);
            T tempor = temp[i];
            temp[i] = temp[randSpot];
            temp[randSpot] = tempor;
        }
        for(int i = 0; i < freeSpace; i++){
            deck[i] = temp[i];
        }
    }
    //this is the card holder class
}
