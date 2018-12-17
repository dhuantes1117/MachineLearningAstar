/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import AStar.*;
import TowersOfHanoi.HanoiState;
import TowersOfHanoi.UnitTest;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author dhuant
 */
public class MachineLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UnitTest Nevada = new UnitTest();
        if (Nevada.runAllTests()) {
            return;
        }
        ArrayList<String> Grid = new ArrayList<String>();
        Grid.add("A banana");
        Grid.add("can't drive");
        Grid.add("even for");
        Grid.add("good health");
        Grid.add("it jumps");
        /*Cluster Map = new Cluster();
        HanoiState HState = new HanoiState(0, 0, false, 3);
        HState.getPegs()[0].fill();
        Map.add(HState);
        try {
            FileOutputStream file = new FileOutputStream("maps/blank.txt"); 
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object 
            out.writeObject(Map); 
            out.close(); 
            file.close(); 
            
        } catch (Exception e) {
            System.out.println("Failure");
            e.printStackTrace();
        }*/
        /*
        Cluster RememberMe = new Cluster();
        try {
            ObjectInputStream In = new ObjectInputStream(new FileInputStream("maps/blank.txt"));
            // Method for serialization of object 
            RememberMe = (Cluster) In.readObject(); 
            In.close(); 
            In.close(); 
            
        } catch (Exception e) {
            System.out.println("Failure");
            e.printStackTrace();
        }
        RememberMe.get(0).display();
        */
    }
    
}
