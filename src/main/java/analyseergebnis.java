/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carol
 */
public class analyseergebnis {

    public static boolean analysieren() {
        int zahl = 5;
        double zufall = Math.random() + 5;
        if (zufall <= zahl) {
            return true;
        } else {
            return false;
        }
    }
}
