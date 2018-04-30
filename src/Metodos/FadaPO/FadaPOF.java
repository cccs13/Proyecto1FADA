

package Metodos.FadaPO;

import Metodos.Fada4.FadaCDCua4;
import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class FadaPOF {
    boolean salir;
    private ArrayList <Integer> par = new ArrayList<Integer>();
    public void buscar(){
        salir=false;
        for (int fila = 0; fila < 9; fila++) {            
                buscarPareja(fila);
                if(salir){
                    break;
                }     
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir) new FadaPOC().buscar();
    }
    private void buscarPareja(int fila ){      
            for (int col = 0;col < 9; col++) {                
               if(!Msudoku[fila][col].isEncontrado()){ 
                   quitarEliminar(fila);
                   Msudoku[fila][col].setElimianr(true);
                   if(existePareja(fila,  col) && !serrepitePareja(fila)){
                       eliminar(fila);
                       if(salir){
                           
                           break;
                       }
                    }
               }
            } 
    }
    void quitarEliminar(int fila){      
        for (int col = 0; col < 9; col++) {
          Msudoku[fila][col].setElimianr(false);              
        }        
    }
    private boolean existePareja(int fila,int col){
        int beses=0;              
            for (int c = 0; c <9; c++) {
                par.clear();
                beses=0;
                if(!Msudoku[fila][c].isEncontrado() ){
                    if( c!=col){                        
                        for (int i = 0; i < Msudoku[fila][c].getCandidatos().size(); i++) {            
                            for (int j = 0; j < Msudoku[fila][col].getCandidatos().size(); j++) {
                                if(Msudoku[fila][c].getCandidatos().get(i) == Msudoku[fila][col].getCandidatos().get(j)){ 
                                   beses++;
                                   par.add(Msudoku[fila][c].getCandidatos().get(i)); 
                                   
                                }
                            }
                        }
                        if(beses==2){ 
                            Msudoku[fila][c].setElimianr(true);
                          return true;
                        }
                    }
                }  
        }
        return false;
    }
    private boolean serrepitePareja(int fila){

        for (int c = 0; c < 9; c++) {

             if(!Msudoku[fila][c].isEncontrado() && (!Msudoku[fila][c].isElimianr()) ){
                for (int i = 0; i < Msudoku[fila][c].getCandidatos().size(); i++) {            
                    for (int j = 0; j < par.size(); j++) {
                        if(Msudoku[fila][c].getCandidatos().get(i)== par.get(j)){
                              return true;    
                        }
                    }
                }
            }
        }                  
        return false;
    }
    private void eliminar(int fila){       
        for (int c = 0; c < 9; c++) {
            if(Msudoku[fila][c].isElimianr() && !Msudoku[fila][c].isEncontrado()){
                for (int i = 0; i < Msudoku[fila][c].getCandidatos().size(); i++) {
                    if(Msudoku[fila][c].getCandidatos().get(i) != par.get(0) && Msudoku[fila][c].getCandidatos().get(i) != par.get(1)){
                        salir=true;
                        if(!pista){
                            Ventanas.Principal.dificil=true;
                           Msudoku[fila][c].EliminarCandidato((Integer)Msudoku[fila][c].getCandidatos().get(i));
                            i--;
                            
                            if(jcbmCandidato.isSelected() )imprimirCandidato(fila, c);
                        }else{
                           Msudoku[fila][c].getJtf().setBackground(new Color(253,253,174));
                           Msudoku[fila][c].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                           candi.setText("--");
                          info.setText("Parejas Ocultas");
                           break;
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
