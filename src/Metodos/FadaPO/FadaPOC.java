

package Metodos.FadaPO;

import Metodos.Fada4.FadaCDCua4;
import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class FadaPOC {
      boolean salir;
    private ArrayList <Integer> par = new ArrayList<Integer>();
    public void buscar(){
        salir=false;
        for (int columna = 0; columna < 9; columna++) {
            
                buscarPareja(columna);
                if(salir){ 
                    
                    break;
                }     
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir) new FadaCDCua4().buscar();
    }  
    private void buscarPareja(int columna ){
      
            for (int fil = 0;fil < 9; fil++) {                
               if(!MatrizS[fil][columna].isEncontrado()){ 
                   quitarEliminar(columna);
                   MatrizS[fil][columna].setElimianr(true);
                   if(existePareja(columna,  fil) && !serrepitePareja(columna)){
                       eliminar(columna);
                       if(salir){                           
                           break;
                       }
                    }
               }
            }
    }
    void quitarEliminar(int columna){      
            for (int fil = 0; fil < 9; fil++) {
              MatrizS[fil][columna].setElimianr(false);              
                      
            } 
    }
    private boolean existePareja(int columna,int fil){
        int beses=0;              
            for (int f = 0; f <9; f++) {
                par.clear();
                beses=0;
                if(!MatrizS[f][columna].isEncontrado() ){
                    if( f!=fil){                        
                        for (int i = 0; i < MatrizS[f][columna].getCandidatos().size(); i++) {            
                            for (int j = 0; j < MatrizS[fil][columna].getCandidatos().size(); j++) {
                                if(MatrizS[f][columna].getCandidatos().get(i) == MatrizS[fil][columna].getCandidatos().get(j)){ 
                                   beses++;
                                   par.add(MatrizS[f][columna].getCandidatos().get(i)); 
                                   
                                }
                               
                            }
                        }
                        if(beses==2){ 
                            MatrizS[f][columna].setElimianr(true);
                          return true;
                        }
                    }
                } 
        }
        return false;
    }
    private boolean serrepitePareja(int columna){
                   
            for (int f = 0; f < 9; f++) {
                
                 if(!MatrizS[f][columna].isEncontrado() && (!MatrizS[f][columna].isElimianr()) ){
                    for (int i = 0; i < MatrizS[f][columna].getCandidatos().size(); i++) {            
                            for (int j = 0; j < par.size(); j++) {
                                if(MatrizS[f][columna].getCandidatos().get(i)== par.get(j)){
                                     return true;    
                                }
                            }
                         }
                 }
            }  
         return false;
    }
    private void eliminar(int columna){
       
        for (int f = 0; f < 9; f++) {
            if(MatrizS[f][columna].isElimianr() && !MatrizS[f][columna].isEncontrado()){
                for (int i = 0; i < MatrizS[f][columna].getCandidatos().size(); i++) {
                    if(MatrizS[f][columna].getCandidatos().get(i) != par.get(0) && MatrizS[f][columna].getCandidatos().get(i) != par.get(1)){
                        salir=true;
                        if(!pista){
                          MatrizS[f][columna].EliminarCandidato((Integer)MatrizS[f][columna].getCandidatos().get(i));
                            i--;
                            dificil=true;
                            if(jcbmCandidato.isSelected() )imprimirCandidato(f, columna);
                        }else{
                            MatrizS[f][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                           MatrizS[f][columna].getJtf().setBackground(new Color(253,253,174));
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
