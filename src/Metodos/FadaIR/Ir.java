

package Metodos.FadaIR;

import java.util.ArrayList;


public class Ir {
    private int fila;
    private int columna;
    private int numero;
    private int tipo;
    ArrayList<Ir>vol = vol =  new ArrayList<>();;  
    ArrayList<Integer> candidatos = null;
    public Ir(int f, int c, ArrayList<Integer> can,int num,int tipo) {
        this.fila = f;
        this.columna = c;
        this.candidatos = can;
        this.numero = num;
        this.tipo = tipo;
    }

    public Ir(int f, int c, int num) {
        this.fila = f;
        this.columna = c;
        this.numero = num;
    }
    public int getF() {
        return fila;
    }
    public int getC() {
        return columna;
    }
    public int getNum() {
        return numero;
    }

    public ArrayList<Ir> getVol() {
        return vol;
    }

    public ArrayList<Integer> getCan() {
        return candidatos;
    }

    public int getTipo() {
        return tipo;
    }

   public void add(Ir ir){
       vol.add(ir);
   } 

    
}
