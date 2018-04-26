

package Metodos.CuartetosDesnudos;

import Metodos.Sencillos.SencilloAlDescubierto;
import Metodos.TriosAlDescubierto.TriAlDescubiertoColunma_2;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class CuartetosDesnudosColumna_4 {
        boolean salir;
    ArrayList<Integer> tem;
    int beses;
    public void buscar(){
        salir=false;
        for (int columa = 0; columa < 9; columa++) {
            buscarCuartetos(columa);
            if(salir)break;
        }
        if(salir && !pista) new SencilloAlDescubierto().buscarSencillo();
        else if(!salir)new CuartetosDesnudosCuadro_3().buscar();
    }
    private void buscarCuartetos(int columna){        
        for (int fil = 0; fil < 9; fil++) {
            tem = Msudoku[fil][columna].getCandidatos();
            if(!Msudoku[fil][columna].isEncontrado() && tem.size()==4){  
                beses=1;
                quitareliminar(columna);
                Msudoku[fil][columna].setElimianr(false);
                existeCuarteto(columna, fil);
                if(beses==4){
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
             Msudoku[fila][columna].setElimianr(true);
        }
    }
    void existeCuarteto(int columa, int fil){
        for (int fila = 0; fila < 9; fila++) {
          if(fila != fil){
              if(!Msudoku[fila][columa].isEncontrado() && Msudoku[fila][columa].getCandidatos().size()<5 ){
                  if(iguales(Msudoku[fila][columa].getCandidatos())){
                        Msudoku[fila][columa].setElimianr(false);
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
           if(Msudoku[fila][columna].isElimianr() && !Msudoku[fila][columna].isEncontrado()) {
                ArrayList<Integer>aux =  Msudoku[fila][columna].getCandidatos();
                   for (int i = 0; i < tem.size(); i++) {            
                      for (int j = 0; j < aux.size(); j++) {
                          if(tem.get(i) == aux.get(j)){
                               salir = true;
                               if(!pista){
                                  Msudoku[fila][columna].EliminarCandidato((Integer)aux.get(j));
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
                                pintar =true;
                                Msudoku[fila][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
            info.setText("Exclusión basada en Cuartetos desnudos");
        }
 
     }
     private void pintar(int columna){
        for (int f = 0; f < 9; f++) {         
            if(!Msudoku[f][columna].isEncontrado() && !Msudoku[f][columna].isElimianr()){
                Msudoku[f][columna].getJtf().setBackground(new Color(69,196,84));
            } 
        }   
    }
}

