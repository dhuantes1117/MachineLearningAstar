/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowersOfHanoi;

import java.util.ArrayList;

/**
 *
 * @author dhuant
 */
public class Peg extends ArrayList<Disc>{
    private int n;
    public Peg(int n) {
        this.n = n;
    }
    @Override
    public boolean add(Disc O){
        if (this.size() < 2) {
            if (legal(O)) {
             return super.add(O);
            }
        }
        return false;
    }
    
    @Override
    public boolean remove(Object O){
        if (this.get(this.size() - 1).equals(O)) {
            return super.remove(O);
        }
        return false;
    }
    
    private boolean legal(Disc i) {
        if(this.get(this.size() - 1).getSize() > i.getSize()){
            return true;
        } else {
            return false;
        }
    }
}
