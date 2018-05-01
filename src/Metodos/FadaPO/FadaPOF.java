

package Metodos.FadaPO;

import Metodos.Fada4.FadaCDCua4;
import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


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
               if(!MatrizS[fila][col].isEncontrado()){ 
                   quitarEliminar(fila);
                   MatrizS[fila][col].setElimianr(true);
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
          MatrizS[fila][col].setElimianr(false);              
        }        
    }
    private boolean existePareja(int fila,int col){
        int beses=0;              
            for (int c = 0; c <9; c++) {
                par.clear();
                beses=0;
                if(!MatrizS[fila][c].isEncontrado() ){
                    if( c!=col){                        
                        for (int i = 0; i < MatrizS[fila][c].getCandidatos().size(); i++) {            
                            for (int j = 0; j < MatrizS[fila][col].getCandidatos().size(); j++) {
                                if(MatrizS[fila][c].getCandidatos().get(i) == MatrizS[fila][col].getCandidatos().get(j)){ 
                                   beses++;
                                   par.add(MatrizS[fila][c].getCandidatos().get(i)); 
                                   
                                }
                            }
                        }
                        if(beses==2){ 
                            MatrizS[fila][c].setElimianr(true);
                          return true;
                        }
                    }
                }  
        }
        return false;
    }
    private boolean serrepitePareja(int fila){

        for (int c = 0; c < 9; c++) {

             if(!MatrizS[fila][c].isEncontrado() && (!MatrizS[fila][c].isElimianr()) ){
                for (int i = 0; i < MatrizS[fila][c].getCandidatos().size(); i++) {            
                    for (int j = 0; j < par.size(); j++) {
                        if(MatrizS[fila][c].getCandidatos().get(i)== par.get(j)){
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
            if(MatrizS[fila][c].isElimianr() && !MatrizS[fila][c].isEncontrado()){
                for (int i = 0; i < MatrizS[fila][c].getCandidatos().size(); i++) {
                    if(MatrizS[fila][c].getCandidatos().get(i) != par.get(0) && MatrizS[fila][c].getCandidatos().get(i) != par.get(1)){
                        salir=true;
                        if(!pista){
                            Ventanas.Principal.dificil=true;
                           MatrizS[fila][c].EliminarCandidato((Integer)MatrizS[fila][c].getCandidatos().get(i));
                            i--;
                            
                            if(jcbmCandidato.isSelected() )imprimirCandidato(fila, c);
                        }else{
                           MatrizS[fila][c].getJtf().setBackground(new Color(253,253,174));
                           MatrizS[fila][c].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
        MatrizS[f][c].getJtf().setText(null);
        for (int i = 0; i <MatrizS[f][c].getCandidatos().size() ; i++) {
            MatrizS[f][c].getJtf().append(" "+MatrizS[f][c].getCandidatos().get(i));
        }
    }
}
