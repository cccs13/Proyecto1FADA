

package Metodos.Fada3;

import Metodos.FadaPO.FadaPOCua;
import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class FadaTDC3 {
    boolean salir;
    ArrayList<Integer> tem;
    int beses;
    public void buscar(){
        salir=false;
        for (int columa = 0; columa < 9; columa++) {
            buscarTrio(columa);
            if(salir)break;
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir)new FadaTDC2().buscar();
    }
    private void buscarTrio(int columna){        
        for (int fil = 0; fil < 9; fil++) {
            tem = MatrizS[fil][columna].getCandidatos();
            if(!MatrizS[fil][columna].isEncontrado() && tem.size()==3){  
                beses=1;
                quitareliminar(columna);
                MatrizS[fil][columna].setElimianr(false);
                existeTrio(columna, fil);
                if(beses==3){
                    eliminarCandidatos(columna);
                    if(salir){
                        break;
                    }
                }
            }
        }
    }
    void quitareliminar(int columna){
        for (int fila = 0; fila < 9; fila++) {
             MatrizS[fila][columna].setElimianr(true);
        }
    }
    void existeTrio(int columa, int fil){
        for (int fila = 0; fila < 9; fila++) {
          if(fila != fil){
              if(!MatrizS[fila][columa].isEncontrado() && MatrizS[fila][columa].getCandidatos().size()<4 ){
                  if(iguales(MatrizS[fila][columa].getCandidatos())){
                        MatrizS[fila][columa].setElimianr(false);
                        beses++; 
                   }
              }
          }  
        }
   
    }
     boolean iguales( ArrayList<Integer> tri){
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
     void eliminarCandidatos(int columna){
         boolean pintar=false;
         for (int fila = 0; fila < 9; fila++) {
           if(MatrizS[fila][columna].isElimianr() && !MatrizS[fila][columna].isEncontrado()) {
                ArrayList<Integer>aux =  MatrizS[fila][columna].getCandidatos();
                   for (int i = 0; i < tem.size(); i++) {            
                      for (int j = 0; j < aux.size(); j++) {
                          if(tem.get(i) == aux.get(j)){
                               salir = true;
                               if(!pista){
                                  MatrizS[fila][columna].EliminarCandidato((Integer)aux.get(j));
                                  j--;
                                  dificil=true;
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
            pintar(columna);
            candi.setText("--");
            info.setText("Exclusión basada en Trios Desnudos");
        }
 
     }
     private void pintar(int columna){
        for (int f = 0; f < 9; f++) {         
            if(!MatrizS[f][columna].isEncontrado() && !MatrizS[f][columna].isElimianr()){
                MatrizS[f][columna].getJtf().setBackground(new Color(69,196,84));
            } 
        }   
    }
}
