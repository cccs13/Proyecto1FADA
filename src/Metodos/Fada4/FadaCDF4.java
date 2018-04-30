
package Metodos.Fada4;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class FadaCDF4 {
    boolean salir;
     ArrayList<Integer> tem;
     int beses;
    public void buscar(){
        salir=false;
        for (int fila = 0; fila < 9; fila++) {
            buscarCuartetos(fila);
            if(salir)break;
        }
       if(salir && !pista) new Fada1D().buscarSencillo();
       else if(!salir) new FadaCDC4().buscar();
    }
    private void buscarCuartetos(int fila){
                
        for (int col = 0; col < 9; col++) {
            tem = Msudoku[fila][col].getCandidatos();
            if(!Msudoku[fila][col].isEncontrado() && tem.size()==4){  
                beses=1;
                quitareliminar(fila);
                Msudoku[fila][col].setElimianr(false);
                existeCuartetos(fila, col);
                if(beses==4){
                    eliminarCandidatos(fila);
                    if(salir){
                        break;
                    }
                }
            }
        }
    }
     void existeCuartetos(int fila, int col){
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
      private boolean iguales( ArrayList<Integer> cuar){
        boolean noIgual;
        for (int i = 0; i < cuar.size(); i++) {
            noIgual= false;
            for (int j = 0; j < tem.size(); j++) {
                if(cuar.get(i) == tem.get(j)){
                    noIgual = true;
                    break;                    
                }
            }
           if(!noIgual) return false; 
        }       
        return true;
    }
   void quitareliminar(int fila){
        for (int col = 0; col < 9; col++) {
             Msudoku[fila][col].setElimianr(true);
        }
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
