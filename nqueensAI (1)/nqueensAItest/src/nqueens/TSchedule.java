/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nqueens;

/**
 *
 * @author Mike
 */
public class TSchedule {
    private int limit;
    private double k, lambda;
    public TSchedule(double k, double lambda, int limit) {
        this.k = k;
        this.lambda = lambda;
        this.limit = limit;
    }

    TSchedule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public double T(int t) {
        if (t > limit) {
            return 0.0;
        } else {
            return k * Math.exp(-lambda * t);
        }
    }
    
}