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
public class Disc {
    private final int size;
    private final int color;
    private final int[] finalLocation;
    public Disc(int s) {
        size = s;
        color = new Color(s).getRGB();
        finalLocation = new int[] {2, 0};
    }
    public Disc(int s, int c) {
        size = s;
        color = c;
        finalLocation = new int[] {2, 0};
    }
    public Disc(int s, Color c) {
        size = s;
        color = c.getRGB();
        finalLocation = new int[] {2, 0};
    }
    public Disc(int s, int[] f) {
        size = s;
        color = new Color(s).getRGB();
        finalLocation = f;
    }
    public Disc(int s, int c, int[] f) {
        size = s;
        color = c;
        finalLocation = f;
    }
    public Disc(int s, Color c, int[] f) {
        size = s;
        color = c.getRGB();
        finalLocation = f;
    }
    public int manhattan(int i, int j){
        return Math.abs(i - finalLocation[0]) + Math.abs(j - finalLocation[1]);
    }
}
