

package Metodos.Fada4;

import Metodos.Fada1.Fada1D;
import Metodos.Fada3.FadaTDC3;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class FadaCDF3 {
    private boolean salir;
    private ArrayList<Integer> tem = new ArrayList<Integer>();
    private int beses;
    private ArrayList<Integer> NoExisten = new ArrayList<>();
    public void buscar(){
        salir=false;
        for (int fila = 0; fila < 9; fila++) {
            existeNNumeros(fila);
            buscarTrio(fila);
            if(salir)break;
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir) new FadaCDC3().buscar();
    }
    private void buscarTrio(int fila){        
        for (int col = 0; col < 9; col++) {
            tem.clear();
            tem = (ArrayList<Integer>) Msudoku[fila][col].getCandidatos().clone();
            if(!Msudoku[fila][col].isEncontrado() && tem.size()==3){ 
                for (int i = 0; i < NoExisten.size(); i++) {
                    beses=1;
                    if(completarTrio(NoExisten.get(i))){
                        quitareliminar(fila);
                        Msudoku[fila][col].setElimianr(false);
                        existeTrio(fila, col);
                        if(beses==4){
                            eliminarCandidatos(fila);
                            if(salir){
                                col=9;                                
                                break;
                            }
                        }
                        tem.remove(3);
                    }
                }
            }
        }
    
    }
    private void quitareliminar(int fila){
        for (int col = 0; col < 9; col++) {
             Msudoku[fila][col].setElimianr(true);
        }
    }
    private void existeTrio(int fila, int col){
        for (int columna = 0; columna < 9; columna++) {
            if(columna != col){
                if(!Msudoku[fila][columna].isEncontrado() && Msudoku[fila][columna].getCandidatos().size()<5 ){
                    if(iguales(Msudoku[fila][columna].getCandidatos())){
                        Msudoku[fila][columna].setElimianr(false);
                        beses++; 
                    }
                }
            }
        }
    
    }
    private boolean iguales( ArrayList<Integer> tri){
        boolean noIgual;
        for (int i = 0; i < tri.size(); i++) {
            noIgual= false;
            for (int j = 0; j < tem.size(); j++) {
                if(tri.get(i) == tem.get(j)){
                    noIgual = true;
                    break;                    
                }
            }
           if(!noIgual) return false; 
        }       
        return true;
    }
    private void existeNNumeros(int fila){
        NoExisten.clear();
        for (int i = 1; i <= 9; i++) {
           NoExisten.add(i); 
        }
        for (int col = 0 ;col < 9; col++) {           
            if(Msudoku[fila][col].isEncontrado()){
                NoExisten.remove(((Integer)Msudoku[fila][col].getNumero()));
            }
        }
    }
     private boolean completarTrio(int num){        
        for (int i = 0; i < tem.size(); i++) {
            if(tem.get(i)==num){                
               return false;
            }
        }
     tem.add(num);
     return true;       
    }
     void eliminarCandidatos(int fila){
         boolean pintar=false;
         for (int columna = 0; columna < 9; columna++) {
            if(Msudoku[fila][columna].isElimianr() && !Msudoku[fila][columna].isEncontrado()) {
                ArrayList<Integer>aux =  Msudoku[fila][columna].getCandidatos();
                for (int i = 0; i < tem.size(); i++) {            
                    for (int j = 0; j < aux.size(); j++) {
                        if(tem.get(i) == aux.get(j)){
                            salir=true;
                            if(!pista){
                                Msudoku[fila][columna].EliminarCandidato(aux.get(j));
                                j--;   
                                dificil=true;
                                if(jcbmCandidato.isSelected()){
                                    Msudoku[fila][columna].getJtf().setText(null);
                                   for (int h = 0; h < Msudoku[fila][columna].getCandidatos().size(); h++) {
                                     Msudoku[fila][columna].getJtf().append(" "+ Msudoku[fila][columna].getCandidatos().get(h));
                                   }
                                }
                             }
                            else{
                                Msudoku[fila][columna].getJtf().setBackground(new Color(253,253,174)); 
                                Msudoku[fila][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                pintar =true;
                                i=9;
                                break;    
                            }
                        }
                    }
                }
            }
         }
         if(pintar && pista){
            pintar(fila);
            candi.setText("--");
            info.setText("Exclusión basada en Cuartetos desnudos");
        }
     }
     private void pintar(int fila){
        for (int c = 0; c < 9; c++) {         
            if(!Msudoku[fila][c].isEncontrado() && !Msudoku[fila][c].isElimianr()){
                Msudoku[fila][c].getJtf().setBackground(new Color(69,196,84));
            } 
        }   
    }  
}
