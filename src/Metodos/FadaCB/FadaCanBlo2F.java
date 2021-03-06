

package Metodos.FadaCB;

import Metodos.Fada1.Fada1D;
import Metodos.Fada3.FadaTDCua3;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import static Ventanas.Principal.MatrizS;


public class FadaCanBlo2F {
   boolean salir = false;
    public void buscar(){
        for (int fila = 0; fila < 9; fila++) {
            buscarCandidatos(fila);
           if(salir)break; 
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir)new FadaTDCua3().buscar();
    }
    public void buscarCandidatos(int fila){
//        existentes(fila);
        for (int columna = 0; columna < 9; columna++) {
            if(!MatrizS[fila][columna].isEncontrado()){
              for (int i = 0; i < MatrizS[fila][columna].getCandidatos().size(); i++) {
                if(!serrepiteNumero(fila, columna,  MatrizS[fila][columna].getCandidatos().get(i))){
                    eliminar(fila, columna, MatrizS[fila][columna].getCandidatos().get(i));
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
                for (int i = 0; i < MatrizS[fila][col].getCandidatos().size(); i++) {
                    if(MatrizS[fila][col].getCandidatos().get(i)== num){
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
                  if(!MatrizS[fil][col].isEncontrado() && fil !=fila){
                      for (int i = 0; i < MatrizS[fil][col].getCandidatos().size(); i++) {
                          if(MatrizS[fil][col].getCandidatos().get(i)==num && fila!=fil){
                              if(!pista){
//                                  Ventanas.Principal.dificil=true;
                                MatrizS[fil][col].EliminarCandidato((Integer)num);
                                if(jcbmCandidato.isSelected()){
                                  imprimirPosibilidades(fil, col);
                                }
                              }
                              else{
                                MatrizS[fil][col].getJtf().setBackground(new Color(253,253,174)); 
                                MatrizS[fil][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
            MatrizS[fila][i].getJtf().setBackground(new Color(69,196,84));
        }
    }
     public void imprimirPosibilidades(int fil, int col){
           if(!MatrizS[fil][col].isEncontrado())
               MatrizS[fil][col].getJtf().setText(null);
            for (int i = 0; i <   MatrizS[fil][col].getCandidatos().size(); i++) {
                  if(!MatrizS[fil][col].isEncontrado())
                       MatrizS[fil][col].getJtf().append(" "+MatrizS[fil][col].getCandidatos().get(i));
            }
    }
}
