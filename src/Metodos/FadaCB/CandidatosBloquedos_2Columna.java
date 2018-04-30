

package Metodos.FadaCB;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class CandidatosBloquedos_2Columna {
  boolean salir = false;
    public void buscarC(){
        salir = false;
        for (int columna = 0; columna < 9; columna++) {
            buscarCandidatos(columna);
            if(salir)break;
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir)new CandidatoBloquedos_2Fila().buscar();
    }
    public void buscarCandidatos(int columna){
        for (int fila = 0; fila < 9; fila++) {
            if(!Msudoku[fila][columna].isEncontrado()){
                for (int i = 0; i < Msudoku[fila][columna].getCandidatos().size(); i++) {
                    if(!serrepiteNumero(fila, columna,  Msudoku[fila][columna].getCandidatos().get(i))){
                       eliminar(fila, columna, Msudoku[fila][columna].getCandidatos().get(i));
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
                for (int i = 0; i < Msudoku[fil][columna].getCandidatos().size(); i++) {
                    if(Msudoku[fil][columna].getCandidatos().get(i)== num){
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
                if(!Msudoku[fil][col].isEncontrado() && fil !=fila){
                    for (int i = 0; i < Msudoku[fil][col].getCandidatos().size(); i++) {
                        if(Msudoku[fil][col].getCandidatos().get(i)==num && col!=columna){
                            if(!pista){
//                                Ventanas.Principal.dificil=true;
                               Msudoku[fil][col].EliminarCandidato((Integer)num);
                               if(jcbmCandidato.isSelected())
                                    imprimirPosibilidades(fil, col);
                            }
                            else{
                                Msudoku[fil][col].getJtf().setBackground(new Color(253,253,174)); 
                                Msudoku[fila][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
            Msudoku[i][columna].getJtf().setBackground(new Color(69,196,84));
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
