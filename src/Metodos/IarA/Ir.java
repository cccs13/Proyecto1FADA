/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodos.IarA;

import java.util.ArrayList;

/**
 *
 * @author fredy-18
 */
public class Ir {
    private int f;
    private int c;
    private int num;
    private int tipo;
    ArrayList<Ir>vol = vol =  new ArrayList<>();;  
    ArrayList<Integer> can = null;
    public Ir(int f, int c, ArrayList<Integer> can,int num,int tipo) {
        this.f = f;
        this.c = c;
        this.can = can;
        this.num = num;
        this.tipo = tipo;
    }

    public Ir(int f, int c, int num) {
        this.f = f;
        this.c = c;
        this.num = num;
    }
    public int getF() {
        return f;
    }
    public int getC() {
        return c;
    }
    public int getNum() {
        return num;
    }

    public ArrayList<Ir> getVol() {
        return vol;
    }

    public ArrayList<Integer> getCan() {
        return can;
    }

    public int getTipo() {
        return tipo;
    }

   public void add(Ir ir){
       vol.add(ir);
   } 

    
}
