
package Metodos.Fada3;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;

//listo
public class FadaTDF2 {
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
        else if(!salir) new FadaTDC3().buscar();
    }
    private void buscarTrio(int fila){        
        for (int col = 0; col < 9; col++) {
            tem.clear();
            tem = (ArrayList<Integer>) MatrizS[fila][col].getCandidatos().clone();
            if(!MatrizS[fila][col].isEncontrado() && tem.size()==2){ 
                for (int i = 0; i < NoExisten.size(); i++) {
                    beses=1;
                    if(completarTrio(NoExisten.get(i))){
                        quitareliminar(fila);
                        MatrizS[fila][col].setElimianr(false);
                        existeTrio(fila, col);
                        if(beses==3){
                            eliminarCandidatos(fila);
                            if(salir){
                                col=9;                                
                                break;
                            }
                        }
                        tem.remove(2);
                    }
                }
            }
        }    
    }
    private void quitareliminar(int fila){
        for (int col = 0; col < 9; col++) {
             MatrizS[fila][col].setElimianr(true);
        }
    }
    private void existeTrio(int fila, int col){
        for (int columna = 0; columna < 9; columna++) {
            if(columna != col){
                if(!MatrizS[fila][columna].isEncontrado() && MatrizS[fila][columna].getCandidatos().size()<3 ){
                    if(iguales(MatrizS[fila][columna].getCandidatos())){
                        MatrizS[fila][columna].setElimianr(false);
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
            if(MatrizS[fila][col].isEncontrado()){
                NoExisten.remove(((Integer)MatrizS[fila][col].getNumero()));
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
            if(MatrizS[fila][columna].isElimianr() && !MatrizS[fila][columna].isEncontrado()) {
                ArrayList<Integer>aux =  MatrizS[fila][columna].getCandidatos();
                for (int i = 0; i < tem.size(); i++) {            
                    for (int j = 0; j < aux.size(); j++) {
                        if(tem.get(i) == aux.get(j)){
                            salir=true;
                            if(!pista){
                                MatrizS[fila][columna].EliminarCandidato(aux.get(j));
                                dificil=true;
                                j--;   
                                if(jcbmCandidato.isSelected()){
                                    MatrizS[fila][columna].getJtf().setText(null);
                                   for (int h = 0; h < MatrizS[fila][columna].getCandidatos().size(); h++) {
                                     MatrizS[fila][columna].getJtf().append(" "+ MatrizS[fila][columna].getCandidatos().get(h));
                                   }
                                }
                             }
                            else{
                                MatrizS[fila][columna].getJtf().setBackground(new Color(253,253,174)); 
                                MatrizS[fila][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
            info.setText("Exclusión basada en Trios Desnudos");
        }
     }
     private void pintar(int fila){
        for (int c = 0; c < 9; c++) {         
            if(!MatrizS[fila][c].isEncontrado() && !MatrizS[fila][c].isElimianr()){
                MatrizS[fila][c].getJtf().setBackground(new Color(69,196,84));
            } 
        }   
    }
}
