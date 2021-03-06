

package Metodos.FadaCB;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class FadaCanBlo2C {
  boolean salir = false;
    public void buscarC(){
        salir = false;
        for (int columna = 0; columna < 9; columna++) {
            buscarCandidatos(columna);
            if(salir)break;
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir)new FadaCanBlo2F().buscar();
    }
    public void buscarCandidatos(int columna){
        for (int fila = 0; fila < 9; fila++) {
            if(!MatrizS[fila][columna].isEncontrado()){
                for (int i = 0; i < MatrizS[fila][columna].getCandidatos().size(); i++) {
                    if(!serrepiteNumero(fila, columna,  MatrizS[fila][columna].getCandidatos().get(i))){
                       eliminar(fila, columna, MatrizS[fila][columna].getCandidatos().get(i));
                       if(salir){
                          fila=9;
                          break;
                       }
                    }
                }
            }
        }                
    }
    boolean serrepiteNumero(int fila, int columna, int num){
        int tem =(int)fila/3;
        for (int fil = 0; fil < 9; fil++) {
            if(fil<tem*3 ||fil>tem*3+2){
                for (int i = 0; i < MatrizS[fil][columna].getCandidatos().size(); i++) {
                    if(MatrizS[fil][columna].getCandidatos().get(i)== num){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    void eliminar(int fila, int columna, int num){
        boolean pintar=false;
        for (int fil = ((int)fila/3)*3; fil <((int)fila/3)*3+3; fil++) {
            for (int col = ((int)columna/3)*3; col <((int)columna/3)*3+3; col++){
                if(!MatrizS[fil][col].isEncontrado() && fil !=fila){
                    for (int i = 0; i < MatrizS[fil][col].getCandidatos().size(); i++) {
                        if(MatrizS[fil][col].getCandidatos().get(i)==num && col!=columna){
                            if(!pista){
//                                Ventanas.Principal.dificil=true;
                               MatrizS[fil][col].EliminarCandidato((Integer)num);
                               if(jcbmCandidato.isSelected())
                                    imprimirPosibilidades(fil, col);
                            }
                            else{
                                MatrizS[fil][col].getJtf().setBackground(new Color(253,253,174)); 
                                MatrizS[fila][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                pintar=true;                                
                            }
                            
                            salir = true;  
                            break;
                        }
                    }
                }
            }
        }
        if(pintar && pista){
            colorPista(columna);
            candi.setText(""+num);
            info.setText("Candidato Bloquiedo (2)");
        }
    }
    void colorPista(int columna){
        for (int i = 0; i < 9; i++) {
            MatrizS[i][columna].getJtf().setBackground(new Color(69,196,84));
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
