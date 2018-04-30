

package Sudoku;

import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.JTextArea;


public class FadaSudokuSecundario {
    private boolean encontrado;
    private boolean elimianr;
    private int numero = 0;
    private ArrayList<Integer> candidatos;
    private JTextArea jtf;

    public FadaSudokuSecundario(boolean encontrado, int numero, JTextArea c) {
        this.encontrado = encontrado;
        this.numero = numero;
        this.candidatos =  new ArrayList<Integer>();
        this.jtf = c;
        this.elimianr = true;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public boolean isElimianr() {
        return elimianr;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Integer> getCandidatos() {
        return candidatos;
    }

    public JTextArea getJtf() {
        return jtf;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCandidato(ArrayList<Integer> candidato) {
        this.candidatos = candidato;
    }

    public void setJtf(JTextArea jtf) {
        this.jtf = jtf;
    }

    public void setElimianr(boolean elimianr) {
        this.elimianr = elimianr;
    }
    
    public void addNunm(int num){
        candidatos.add(num);
    }
    public void EliminarCandidato(Integer candidato){
        candidatos.remove(candidato);
    }
    
}
