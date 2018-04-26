

package Metodos.ParejasOcultas;

import Metodos.CuartetosDesnudos.CuartetosDesnudosCuadro_4;
import Metodos.Sencillos.SencilloAlDescubierto;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class parejasOcultasColumna {
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
        if(salir && !pista) new SencilloAlDescubierto().buscarSencillo();
        else if(!salir) new CuartetosDesnudosCuadro_4().buscar();
    }  
    private void buscarPareja(int columna ){
      
            for (int fil = 0;fil < 9; fil++) {                
               if(!Msudoku[fil][columna].isEncontrado()){ 
                   quitarEliminar(columna);
                   Msudoku[fil][columna].setElimianr(true);
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
              Msudoku[fil][columna].setElimianr(false);              
                      
            } 
    }
    private boolean existePareja(int columna,int fil){
        int beses=0;              
            for (int f = 0; f <9; f++) {
                par.clear();
                beses=0;
                if(!Msudoku[f][columna].isEncontrado() ){
                    if( f!=fil){                        
                        for (int i = 0; i < Msudoku[f][columna].getCandidatos().size(); i++) {            
                            for (int j = 0; j < Msudoku[fil][columna].getCandidatos().size(); j++) {
                                if(Msudoku[f][columna].getCandidatos().get(i) == Msudoku[fil][columna].getCandidatos().get(j)){ 
                                   beses++;
                                   par.add(Msudoku[f][columna].getCandidatos().get(i)); 
                                   
                                }
                               
                            }
                        }
                        if(beses==2){ 
                            Msudoku[f][columna].setElimianr(true);
                          return true;
                        }
                    }
                } 
        }
        return false;
    }
    private boolean serrepitePareja(int columna){
                   
            for (int f = 0; f < 9; f++) {
                
                 if(!Msudoku[f][columna].isEncontrado() && (!Msudoku[f][columna].isElimianr()) ){
                    for (int i = 0; i < Msudoku[f][columna].getCandidatos().size(); i++) {            
                            for (int j = 0; j < par.size(); j++) {
                                if(Msudoku[f][columna].getCandidatos().get(i)== par.get(j)){
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
            if(Msudoku[f][columna].isElimianr() && !Msudoku[f][columna].isEncontrado()){
                for (int i = 0; i < Msudoku[f][columna].getCandidatos().size(); i++) {
                    if(Msudoku[f][columna].getCandidatos().get(i) != par.get(0) && Msudoku[f][columna].getCandidatos().get(i) != par.get(1)){
                        salir=true;
                        if(!pista){
                          Msudoku[f][columna].EliminarCandidato((Integer)Msudoku[f][columna].getCandidatos().get(i));
                            i--;
                            dificil=true;
                            if(jcbmCandidato.isSelected() )imprimirCandidato(f, columna);
                        }else{
                            Msudoku[f][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                           Msudoku[f][columna].getJtf().setBackground(new Color(253,253,174));
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
