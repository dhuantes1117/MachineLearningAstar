/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowersOfHanoi;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author dhuant
 */
public class Peg extends ArrayList<Disc>{
    private int n;
    private Color[] Colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.CYAN, Color.GREEN};
    public Peg() {
        
    }
    public Peg(int n) {
        this.n = n;
    }
    @Override
    public boolean add(Disc O){
        if (this.size() < n()) {
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
        if (this.isEmpty()) {
            return true;
        }
        if(this.get(this.size() - 1).getSize() > i.getSize()){
            return true;
        } else {
            return false;
        }
    }
    
    public void fill() {
        for (int i = 0; i < n(); i++) {
        if (n() < Colors.length) {
            this.add(new Disc(n() - i, n()));
        } else {
            this.add(new Disc(n() - i));
        }
        }
    }

    /**
     * @return the n
     */
    public int n() {
        return n;
    }
}
