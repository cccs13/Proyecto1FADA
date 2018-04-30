

package Metodos.Fada2;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


// exclusion basada en candidatos bloquedo 1
public class FadaPDF {
    int parejas =1;
    int posi1,posi2;
    boolean iguales;
    ArrayList<Integer>pareja;
    boolean salir;
    public  void parejas(){
        salir=false;
        for (int fila = 0; fila < 9; fila++) {            
                buscarPareja1(fila);
                if(salir)break;
            
 
        }
        
        if(salir && !pista)new Fada1D().buscarSencillo();
        else if(!salir) new FadaPDC().parejas();
    }
    void buscarPareja1(int fila){
        for (int co = 0; co < 9; co++) {            
                if(Msudoku[fila][co].getCandidatos().size() ==2){
                   parejas=1;
                   iguales = false; 
                    buscarPareja2(fila, co);
                       if(parejas==2 && iguales){
                           eliminar(fila, co);
                           if(salir)break;
                       }
                }           
        }
    }
    
    void buscarPareja2(int fila, int co){
          for (int colum = 0; colum < 9; colum++) {
              if(Msudoku[fila][colum].getCandidatos().size() ==2 && co!=colum){
                iguales=  compararParejas(Msudoku[fila][colum].getCandidatos(),Msudoku[fila][co].getCandidatos());
                if(iguales){
                       posi2= colum;
                        pareja =Msudoku[fila][co].getCandidatos();
                       
                    }
                }
           }
      }
    boolean compararParejas(ArrayList<Integer> pareja1,ArrayList<Integer> pareja2){        
       if(pareja1.get(0) == pareja2.get(0) || pareja1.get(0) == pareja2.get(1)){
            if(pareja1.get(1) == pareja2.get(0) || pareja1.get(1) == pareja2.get(1)){
                 parejas++;
//               System.out.println("Hat pareja 2");
                 return true;
            }
       }
        return false;
    }
    void eliminar(int fila , int p2){ 
        boolean pintar=false;
            for (int co = 0; co < 9; co++) {
                if(!Msudoku[fila][co].isEncontrado()){
                    if((co!=p2 && co!=posi2)){                        
                        ArrayList<Integer> tem = Msudoku[fila][co].getCandidatos();
                        for (int i = 0; i < tem.size(); i++) {
                            if(tem.get(i)==pareja.get(0) || tem.get(i)==pareja.get(1) ){
                                salir=true;
                                if(!pista){
                                    Msudoku[fila][co].EliminarCandidato(tem.get(i));
                                    if(jcbmCandidato.isSelected()) imprimirCandidatos(fila, co);
                                    i--;                                 
                                }                                 
                                else{
                                    Msudoku[fila][co].getJtf().setBackground(new Color(253,253,174));
                                    Msudoku[fila][co].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                    pintar =true;
                                    break;
                                }
                                
                            }
                        }
                }
            }
        }
        if(pintar && pista){
            color(fila, p2);
            color(fila, posi2);
            candi.setText("--");
            info.setText("Exclusión basada en Parejas Desnudas");
        }
    }
    void color(int fil, int col){
       Msudoku[fil][col].getJtf().setBackground(new Color(69,196,84));
    }
    public void imprimirCandidatos(int fil, int col){
           if(!Msudoku[fil][col].isEncontrado())
               Msudoku[fil][col].getJtf().setText(null);
            for (int i = 0; i <   Msudoku[fil][col].getCandidatos().size(); i++) {
                if(!Msudoku[fil][col].isEncontrado())
                    Msudoku[fil][col].getJtf().append(" "+Msudoku[fil][col].getCandidatos().get(i));            
           }          
    }
}
