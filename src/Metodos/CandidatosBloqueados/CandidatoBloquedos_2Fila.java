

package Metodos.CandidatosBloqueados;

import Metodos.Sencillos.SencilloAlDescubierto;
import Metodos.TriosAlDescubierto.TrioAldescubiertoCuadro_3;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;


public class CandidatoBloquedos_2Fila {
   boolean salir = false;
    public void buscar(){
        for (int fila = 0; fila < 9; fila++) {
            buscarCandidatos(fila);
           if(salir)break; 
        }
        if(salir && !pista) new SencilloAlDescubierto().buscarSencillo();
        else if(!salir)new TrioAldescubiertoCuadro_3().buscar();
    }
    public void buscarCandidatos(int fila){
//        existentes(fila);
        for (int columna = 0; columna < 9; columna++) {
            if(!Msudoku[fila][columna].isEncontrado()){
              for (int i = 0; i < Msudoku[fila][columna].getCandidatos().size(); i++) {
                if(!serrepiteNumero(fila, columna,  Msudoku[fila][columna].getCandidatos().get(i))){
                    eliminar(fila, columna, Msudoku[fila][columna].getCandidatos().get(i));
                    if(salir){
                        columna = 9;
                        break;
                    }
                }
             }
             
            }            
        }
    }
    boolean serrepiteNumero(int fila, int columna, int num){
        int tem =(int)columna/3;
        for (int col = 0; col < 9; col++) {
            if(col<tem*3 ||col>tem*3+2){
                for (int i = 0; i < Msudoku[fila][col].getCandidatos().size(); i++) {
                    if(Msudoku[fila][col].getCandidatos().get(i)== num){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    void eliminar(int fila, int columna, int num){
        boolean pintar =false;
        for (int fil = ((int)fila/3)*3; fil <((int)fila/3)*3+3; fil++) {
            for (int col = ((int)columna/3)*3; col <((int)columna/3)*3+3; col++){
                  if(!Msudoku[fil][col].isEncontrado() && fil !=fila){
                      for (int i = 0; i < Msudoku[fil][col].getCandidatos().size(); i++) {
                          if(Msudoku[fil][col].getCandidatos().get(i)==num && fila!=fil){
                              if(!pista){
//                                  Ventanas.Principal.dificil=true;
                                Msudoku[fil][col].EliminarCandidato((Integer)num);
                                if(jcbmCandidato.isSelected()){
                                  imprimirPosibilidades(fil, col);
                                }
                              }
                              else{
                                Msudoku[fil][col].getJtf().setBackground(new Color(253,253,174)); 
                                Msudoku[fil][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                pintar=true; 
                              }                                                            
                              salir=true; 
                              break;                                 
                          }
                      }
                  }
            } 
        }
        if(pintar && pista){
           colorPista(fila);
            candi.setText(""+num);
            info.setText("Candidato Bloquiedo (2)");  
        }
    }
    void colorPista(int fila){
        for (int i = 0; i < 9; i++) {
            Msudoku[fila][i].getJtf().setBackground(new Color(69,196,84));
        }
    }
     public void imprimirPosibilidades(int fil, int col){
           if(!Msudoku[fil][col].isEncontrado())
               Msudoku[fil][col].getJtf().setText(null);
            for (int i = 0; i <   Msudoku[fil][col].getCandidatos().size(); i++) {
                  if(!Msudoku[fil][col].isEncontrado())
                       Msudoku[fil][col].getJtf().append(" "+Msudoku[fil][col].getCandidatos().get(i));
            }
    }
}
