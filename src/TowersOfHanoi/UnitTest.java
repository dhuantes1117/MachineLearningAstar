/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowersOfHanoi;

import java.awt.Color;
import AStar.*;
import java.util.ArrayList;
/**
 *
 * @author dhuant
 */
public class UnitTest {
    public UnitTest(){
        
    }
    
    public boolean runAllTests(){
        try {
            createState();
            moveDisc();
            checkPermutations();
        } catch (Exception e) {
            System.out.println("UnitTest has encountered an error:\n" + e.getMessage());
            //e.printStackTrace();
            return true;
        }
        System.out.println("Unit Test has succesfully completed, no errors were detected");
        return false;
    }
    
    public void createState() throws Exception{
        HanoiState Towers = new HanoiState(0, 0, true, 3);
        Towers.getPegs()[0].fill();
        boolean flag = false;
        if (Towers.getPegs()[0].size() == 3) {
            for (int i = 0; i < Towers.getPegs()[0].size(); i++) {
                if (!(i + 1 == Towers.getPegs()[0].size()) && (Towers.getPegs()[0].get(i).getSize() > Towers.getPegs()[0].get(i+1).getSize())) {
                    
                } else {
                    if ((i + 1 == Towers.getPegs()[0].size())) {
                        
                    } else {
                        flag = true;
                    }
                }
            }
        } else {
            throw new Exception("createState did not correctly initialize Pegs[]");
        }
        if (flag) {
            throw new Exception("createState did not correctly initialize discs of decreasing size on Peg 0");
        }
    }
    
    public void moveDisc() throws Exception{
        HanoiState Towers = new HanoiState(0, 0, true, 3);
        Towers.getPegs()[0].fill();
        Disc Top = Towers.getPegs()[0].get(Towers.getPegs()[0].size() - 1);
        Towers.moveDisc(Top, 2);
        if (Towers.getPegs()[0].size() == 2) {
            if (Towers.getPegs()[2].size() == 1) {
                if (Towers.getPegs()[2].get(0).equals(Top)) {
                    
                } else {
                    throw new Exception("moveDisc did not move the topmost disc.\nIt moved the " + Towers.getPegs()[2].get(0).getColor() + " disc");
                }
            } else {
                throw new Exception("moveDisc did not add a disc to peg two");
            }
        } else {
            throw new Exception("moveDisc did not remove a disc from peg zero");
        }
    }
    //figure out how to have all discs read thieir final state from "Solution" Node, but move
    //away from actually comparing if possible. Look into organizing nodes in XY coordinate system
    //and combining display() of multiple states. Think of problems you couldn't code by hand
    public void checkPermutations() throws Exception {
        HanoiState S1 = new HanoiState(3, 0);
        S1.setName("S1");
        S1.permute();
        ArrayList<Node> ActualPermutations = S1.getNeighborNodes();
        ArrayList<Node> TheoreticalPermutations = new ArrayList<>();
        HanoiState First = new HanoiState(3, 0);
        HanoiState Second = new HanoiState(3, 0);
        First.moveDisc(0, 1);
        Second.moveDisc(0, 2);
        TheoreticalPermutations.add(First);
        TheoreticalPermutations.add(Second);
        if (ActualPermutations.size() != TheoreticalPermutations.size()) {
            throw new Exception("Permuated State was not expanded correctly:\n"+
                    "Size of Theoretical: " + TheoreticalPermutations.size() +
                    "     Size of Actual: " + ActualPermutations.size());
        }
        if (!ActualPermutations.containsAll(TheoreticalPermutations)) {
            throw new Exception("Permuated State was not expanded correctly:\n" +
                        "One or more elements in Theoretical do not match Actual");
        }
    }
    
    public void navigablePermutations() throws Exception {
        
    }
    
    public void expandToDestination() throws Exception {
        
    }
    
    public void updateMap() throws Exception {
        
    }
}
