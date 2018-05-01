

package Metodos.FadaPO;

import Metodos.Fada1.Fada1D;
import Ventanas.Principal;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static Ventanas.Principal.MatrizS;


public class FadaPOCua {
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
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir) new FadaPOF().buscar();
    }
    private void buscarPareja(int fila, int columna ){
        for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {                
               if(!MatrizS[fil][col].isEncontrado()){ 
                   quitarEliminar(fila, columna);
                   MatrizS[fil][col].setElimianr(true);
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
              MatrizS[fil][col].setElimianr(false);              
            } 
       }
    }
    private boolean existePareja(int fila,int fil,int columna,int col){
        int beses=0;       
        for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
                par.clear();
                beses=0;
                if(!MatrizS[f][c].isEncontrado() ){
                    if(f!=fil || c!=col){                        
                        for (int i = 0; i < MatrizS[f][c].getCandidatos().size(); i++) {            
                            for (int j = 0; j < MatrizS[fil][col].getCandidatos().size(); j++) {
                                if(MatrizS[f][c].getCandidatos().get(i) == MatrizS[fil][col].getCandidatos().get(j)){ 
                                   beses++;
                                   par.add(MatrizS[f][c].getCandidatos().get(i));                                    
                                }
                               
                            }
                        }
                        if(beses==2){ 
                            MatrizS[f][c].setElimianr(true);
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
                
                 if(!MatrizS[f][c].isEncontrado() && (!MatrizS[f][c].isElimianr()) ){
                    for (int i = 0; i < MatrizS[f][c].getCandidatos().size(); i++) {            
                         for (int j = 0; j < par.size(); j++) {
                              if(MatrizS[f][c].getCandidatos().get(i)== par.get(j)){
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
                if(MatrizS[f][c].isElimianr() && !MatrizS[f][c].isEncontrado()){
                    for (int i = 0; i < MatrizS[f][c].getCandidatos().size(); i++) {
                        if(MatrizS[f][c].getCandidatos().get(i) != par.get(0) && MatrizS[f][c].getCandidatos().get(i) != par.get(1)){
                            salir=true;
                            if(!pista){
                              MatrizS[f][c].EliminarCandidato((Integer)MatrizS[f][c].getCandidatos().get(i));
                                i--;
                                dificil=true;
                                if(jcbmCandidato.isSelected() )imprimirCandidato(f, c);
                            }else{
                               MatrizS[f][c].getJtf().setBackground(new Color(253,253,174));
                               MatrizS[f][c].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
        MatrizS[f][c].getJtf().setText(null);
        for (int i = 0; i <MatrizS[f][c].getCandidatos().size() ; i++) {
            MatrizS[f][c].getJtf().append(" "+MatrizS[f][c].getCandidatos().get(i));
        }
    }
}
                      
           
        
  