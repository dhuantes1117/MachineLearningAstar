/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowersOfHanoi;

import AStar.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author dhuant
 */
public class HanoiState extends Node implements Serializable{
    private String Name;
    private final boolean solution;
    private int n;
    private Peg[] Pegs;
    
    //x and y would be determined later, right?
    //where are these nodes truly located? since manhattan distance is not
    //calculated with x and y but with index, index and finalPosition
    //so: x and y really have no bearing on the actual algorithm, as long as connect
    //is modified and/or done away with, and at least in the case of Hanoi where each move would elicit
    //a g cost of 1, the only true use of an x and a y would be for the purpose of
    //displaying the states in a semi-organized fashion
    public HanoiState(int n, int pegToFill){
        this.n = n;
        this.solution = false;
        this.Pegs = new Peg[] {new Peg(n), new Peg(n), new Peg(n)};
        Pegs[pegToFill].fill();
    }
    
    public HanoiState(Peg[] pegs) {
        super(-1, -1, false);
        this.solution = false;
        this.Pegs = pegs;
    }
    
    public HanoiState(Peg[] pegs, String name) {
        super(-1, -1, false);
        this.solution = false;
        this.Pegs = pegs;
        this.Name = name;
    }
    
    public HanoiState(int x, int y, boolean origin, int n) {
        super(x, y, origin);
        this.solution = false;
        this.n = n;
        this.Pegs = new Peg[] {new Peg(this.n), new Peg(this.n), new Peg(this.n)};
    }
    
    public HanoiState(String Name, int x, int y) {
        super(Name, x, y);
        this.solution = false;
    }
    
    public HanoiState(String Name, int x, int y, boolean origin) {
        super(Name, x, y, origin);
        this.solution = false;
    }
    
    public HanoiState(boolean solution, int x, int y, boolean origin) {
        super(x, y, origin);
        this.solution = solution;
    }
    
    public HanoiState(boolean solution, String Name, int x, int y) {
        super(Name, x, y);
        this.solution = solution;
    }
    
    public HanoiState(boolean solution, String Name, int x, int y, boolean origin) {
        super(Name, x, y, origin);
        this.solution = solution;
    }
    
    public HanoiState(boolean solution, int x, int y) {
        super(x, y);
        this.solution = solution;
    }
    
    @Override
    public void display() {//int j = 0; j < this.Pegs[i].n(); j++
        for (int i = this.Pegs[0].n(); i >= 0 ; i--) {
            for (int j = 0; j < this.Pegs.length; j++) {
                Disc D = null;
                try {
                    D = this.Pegs[j].get(i);
                } catch (Exception e) {
                }
                if (D == null) {
                    System.out.print("  |  ");
                } else {
                    System.out.print("  " + D.getSize() + "  ");
                }
            }
            System.out.println("");
        }
        System.out.println("===============");
    }

    @Override
    public boolean isSolution() {
        return solution;
    }

    //Type is State but really should be HanoiState
    //Unless there is some way to add these as Nodes into the thing
    //Maybe make the return type Node so it has to take HanoiState
    //Because Node is Abstract
    @Override
    public void permute() {
        ArrayList<Disc> Tops = new ArrayList<Disc>();
        HashSet<HanoiState> Retable = new HashSet<>();
        //since using a HashSet of states, changing data and adding altered doesn't change
        //using new with the cloned Pegs may, maybe look into a method to add the
        //altered object as a new copy that doesn't utitlize clone, but instead uses
        //'new' and a cloned version of Pegs, as Pegs is an array and it immutable
        //so using clone is important
        HanoiState Altered = new HanoiState(this.Pegs.clone());
         for (int i = 0; i < Pegs.length; i++) {
            if (i != 0) {
                    Altered.display();
                if (Altered.moveDisc(i, 0)) {
                    Altered.display();
                    Retable.add(new HanoiState(Altered.Pegs.clone(), i+"to0"));
                    System.out.println("Size of Retable is " + Retable.size());
                    Altered.moveDisc(0, i);
                }
            }
            if (i != 1) {
                    Altered.display();
                if (Altered.moveDisc(i, 1)) {
                    Altered.display();
                    Retable.add(new HanoiState(Altered.Pegs.clone(), i+"to1"));
                    System.out.println("Size of Retable is " + Retable.size());
                    Altered.moveDisc(1, i);
                }
            }
            if (i != 2) {
                    Altered.display();
                if (Altered.moveDisc(i, 2)) {
                    Altered.display();
                    Retable.add(new HanoiState(Altered.Pegs.clone(), i+"to2"));
                    System.out.println("Size of Retable is " + Retable.size());
                    Altered.moveDisc(2, i);
                }
            }
        }
        for (HanoiState option : Retable) {
            this.connect(option, 1);
        }
        System.out.println("Permuted has " + this.getNeighbors().size() + " neighbors");
    }

    @Override
    public int setHueristic(State Dest) {
        int a = 0;
        for (int i = 0; i < Pegs.length; i++) {
            for (int j = 0; j < Pegs[i].size(); j++) {
                a += Pegs[i].get(j).manhattan(i, j);
            }
        }
        return a;
    }

    /**
     * @return the pegs
     */
    public Peg[] getPegs() {
        return Pegs;
    }
    
    private int[] findDisc(Disc Desired) {
        int x = -1;
        for (int i = 0; i < Pegs.length; i++) {
            if (Pegs[i].contains(Desired)) {
                x = i;
            }
        }
        return new int[] {x, Pegs[x].indexOf(Desired)};
    }
    
    protected boolean moveDisc(Disc D, int pegdex){
        int current = findDisc(D)[0];
        int pos = findDisc(D)[1];
        if (Pegs[current].remove(D)) {
            if (Pegs[pegdex].add(D)) {
                
            } else {
                //how do I know this doesn't just take it from the middle but add it to the top
                //in which case bc of the overrided add it would just erase it
                //need UnitTest Class
                Pegs[current].set(pos, D);
            }
        }
        return true;
    }
    
    protected boolean moveDisc(int begindex, int endex){
        try {
            Disc D = this.Pegs[begindex].get(this.Pegs[begindex].size() - 1);
            this.Pegs[begindex].remove(D);
            return this.Pegs[endex].add(D);
        } catch (NullPointerException e) {
            //System.out.println("NullPointed!");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("OutOfBounded!");
            return false;
        }
        
    }
    
    @Override
    public boolean equals(Object O) {
        HanoiState S2;
        try {
            S2 = (HanoiState) O;
        } catch (ClassCastException e) {
            return false;
        }
        if (S2.Pegs.length != this.Pegs.length) {
            return false;
        }
        for (int i = 0; i < this.Pegs.length; i++) {
            System.out.println("S2's " + i + "th peg has " + S2.Pegs[i].size() + " discs while");
            System.out.println("this's " + i + "th peg has " + this.Pegs[i].size() + " discs");
            if (S2.Pegs[i].size() != this.Pegs[i].size()) {
                return false;
            }
        }
        for (int i = 0; i < this.Pegs.length; i++) {
            for (int j = 0; j < this.Pegs[i].size(); j++) {
                if (this.Pegs[i].get(j).getSize() != S2.Pegs[i].get(j).getSize()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }
}
