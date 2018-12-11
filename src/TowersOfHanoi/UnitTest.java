/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowersOfHanoi;

import java.awt.Color;

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
        } catch (Exception e) {
            System.out.println("UnitTest has encountered an error:\n" + e.getMessage());
            e.printStackTrace();
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
        Towers.display();
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
}
