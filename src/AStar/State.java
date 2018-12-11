/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AStar;

import java.util.HashSet;

/**
 *
 * @author dhuant
 */
public interface State {
    public void display();
    public boolean isSolution();
    public void permute();
    public int setHueristic(State Dest);
}
