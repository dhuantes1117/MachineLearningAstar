/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import TowersOfHanoi.HanoiState;
import TowersOfHanoi.UnitTest;

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
    }
    
}
