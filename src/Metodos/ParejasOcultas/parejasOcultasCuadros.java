

package Metodos.ParejasOcultas;

import Metodos.Sencillos.SencilloAlDescubierto;
import Ventanas.Principal;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class parejasOcultasCuadros {
    boolean salir;
    private ArrayList <Integer> par = new ArrayList<Integer>();
    public void buscar(){
        salir=false;
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) { 
                buscarPareja(fila, columna);
                if(salir){ 
                    fila=9;
                    break;
                }
            }            
        }
        if(salir && !pista) new SencilloAlDescubierto().buscarSencillo();
        else if(!salir) new parejasOcultasFila().buscar();
    }
    private void buscarPareja(int fila, int columna ){
        for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {                
               if(!Msudoku[fil][col].isEncontrado()){ 
                   quitarEliminar(fila, columna);
                   Msudoku[fil][col].setElimianr(true);
                   if(existePareja(fila, fil, columna, col) && !serrepitePareja(fila, columna)){
                       eliminar(fila, columna);
                       if(salir){
                           fil=9;
                           break;
                       }
                    }
               }
            }            
        } 
    }
    void quitarEliminar(int fila, int columna){
       for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {
              Msudoku[fil][col].setElimianr(false);              
            } 
       }
    }
    private boolean existePareja(int fila,int fil,int columna,int col){
        int beses=0;       
        for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
                par.clear();
                beses=0;
                if(!Msudoku[f][c].isEncontrado() ){
                    if(f!=fil || c!=col){                        
                        for (int i = 0; i < Msudoku[f][c].getCandidatos().size(); i++) {            
                            for (int j = 0; j < Msudoku[fil][col].getCandidatos().size(); j++) {
                                if(Msudoku[f][c].getCandidatos().get(i) == Msudoku[fil][col].getCandidatos().get(j)){ 
                                   beses++;
                                   par.add(Msudoku[f][c].getCandidatos().get(i));                                    
                                }
                               
                            }
                        }
                        if(beses==2){ 
                            Msudoku[f][c].setElimianr(true);
                          return true;
                        }
                    }
                }                
            }
        }
        return false;
    }
    private boolean serrepitePareja(int fila,int columna){
        int be=0;
         for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
                
                 if(!Msudoku[f][c].isEncontrado() && (!Msudoku[f][c].isElimianr()) ){
                    for (int i = 0; i < Msudoku[f][c].getCandidatos().size(); i++) {            
                         for (int j = 0; j < par.size(); j++) {
                              if(Msudoku[f][c].getCandidatos().get(i)== par.get(j)){
                                 be++; 
                             }
                         }
                    }
                 }
            }
         }
         if(be>0)return true;            
         return false;
    }
    private void eliminar(int fila, int columna){
        for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
                if(Msudoku[f][c].isElimianr() && !Msudoku[f][c].isEncontrado()){
                    for (int i = 0; i < Msudoku[f][c].getCandidatos().size(); i++) {
                        if(Msudoku[f][c].getCandidatos().get(i) != par.get(0) && Msudoku[f][c].getCandidatos().get(i) != par.get(1)){
                            salir=true;
                            if(!pista){
                              Msudoku[f][c].EliminarCandidato((Integer)Msudoku[f][c].getCandidatos().get(i));
                                i--;
                                dificil=true;
                                if(jcbmCandidato.isSelected() )imprimirCandidato(f, c);
                            }else{
                               Msudoku[f][c].getJtf().setBackground(new Color(253,253,174));
                               Msudoku[f][c].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                               candi.setText("--");
                              info.setText("Parejas Ocultas");
                               break;
                            }                           
                        }
                    }
                }
            }
        }  
    }    
    private void imprimirCandidato(int f, int c){
        Msudoku[f][c].getJtf().setText(null);
        for (int i = 0; i <Msudoku[f][c].getCandidatos().size() ; i++) {
            Msudoku[f][c].getJtf().append(" "+Msudoku[f][c].getCandidatos().get(i));
        }
    }
}
                      
           
        
  