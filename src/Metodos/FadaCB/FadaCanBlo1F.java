

package Metodos.FadaCB;

import Metodos.Fada1.Fada1O;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


// exclusion basada en candidatos bloquedo 12
public class FadaCanBlo1F {
    int nBeses;
    boolean estaRepetido;
    int idFil;
    ArrayList <Integer>tem;
    int fil;
    boolean salir;
    public  void buscar(){
       salir =false;
       for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                fil=fila;
                buscarCandidatos(fila, columna);
                if(salir)break;
                } 
            if(salir)break;
            
            }
       if(salir && !pista) new Fada1O().buscarSencilloOculto();
       else if(!salir)new FadaCanBlo2C().buscarC();            
    }
    public void buscarCandidatos(int fila, int columna){        
            for (int fil = fila*3; fil < fila*3+3; fil++) {
                serepite(fil, columna);
                if(salir)break;
            }
    }
    public void serepite(int fila, int columas){
        for (int c = columas*3; c < columas*3+3; c++) {
            if(!MatrizS[fila][c].isEncontrado()){
                tem = MatrizS[fila][c].getCandidatos();
                idFil = fila;                
                for (int i = 0; i < tem.size(); i++) {
                    nBeses=1;
                    comparar(fila, columas, c, tem.get(i));
                    if(nBeses>=2 && !existeResCuadro(fila, columas,  tem.get(i) ) && !salir){
                        eliminarNumFil(fila, columas, tem.get(i));
                    }
                    if(salir)break;
                }
            }
            if(salir==true)break;
        }
    }
    public void comparar(int fila, int columna,int c, int candidato){
        for (int cc = columna*3; cc < columna*3+3; cc++) {
            if(cc!=c &&  !MatrizS[fila][cc].isEncontrado()){
                ArrayList<Integer> aux = MatrizS[fila][cc].getCandidatos();
                for (int i = 0; i < aux.size(); i++) {
                    if(aux.get(i) == candidato){
                       nBeses++; 
                    }
                }
            }
        }
    }
    public boolean existeResCuadro(int fila,int columna, int candi){
        for (int i = fil*3; i < fil*3+3; i++) {
            for (int j = columna*3; j < columna*3+3; j++) {
               if(idFil !=i && !MatrizS[i][j].isEncontrado()) {
                    ArrayList<Integer> aux = MatrizS[i][j].getCandidatos();
                    for (int g = 0; g < aux.size(); g++) {
                        if(candi == aux.get(g)){ 
                            return true;
                        }
                    }
                }
            }
        }
        return false; 
    }
    public void eliminarNumFil(int fila,int col, int num){
       ArrayList tem = null;
       boolean pintar=false;
        for (int i =0; i < 9; i++) {
            tem = MatrizS[fila][i].getCandidatos();
             if((i<col*3 || i>(col*3+2))){
                   if(!MatrizS[fila][i].isEncontrado() ){
                        for (int j = 0; j < tem.size(); j++) {
                             if(num == (int)tem.get(j)){
                                 if(!pista){
//                                     Ventanas.Principal.dificil=true;
                                    MatrizS[fila][i].EliminarCandidato((Integer) tem.get(j));                               
                                    if(jcbmCandidato.isSelected())imprimirPosibilidades(fila, i);
                                 }
                                 else{ 
                                    MatrizS[fila][i].getJtf().setBackground(new Color(253,253,174)); 
                                    pintar=true;
                                    MatrizS[fila][i].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                 }                                   
                                 
                                salir=true;                                
                                break;                                
                             }
                        }
                   }
               }
        }
        if(pintar && pista){
            color(fil, col);
            candi.setText(""+num);
            info.setText("Candidato Bloquiedo (1)");
        }                                    
    }
    void color(int fila, int columna){
        for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
               MatrizS[f][c].getJtf().setBackground(new Color(69,196,84));
            }
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
