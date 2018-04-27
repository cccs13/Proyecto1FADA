//proyecto fada
package Metodos.ParejasDesnudas;

import Metodos.CandidatosBloqueados.CandidatoBloquedos_1Columna;
import Metodos.Sencillos.SencilloAlDescubierto;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class ParejasAlDescubiertoColumnas {
    int parejas =1;
    int posi2;
    boolean iguales;
    ArrayList<Integer>pareja;
    boolean salir;
    public  void parejas(){
        salir=false;
        for (int columnas = 0; columnas < 9; columnas++) {            
                buscarPareja1(columnas);
                if(salir)break;
        }
        if(salir && !pista) new SencilloAlDescubierto().buscarSencillo();
        else if(!salir) new CandidatoBloquedos_1Columna().buscar();
    }
    void buscarPareja1(int columna){
        for (int fi = 0; fi < 9; fi++) {            
                if(Msudoku[fi][columna].getCandidatos().size() ==2){
                   parejas=1;
                   iguales = false; 
                    buscarPareja2(columna, fi);
                       if(parejas==2 && iguales){
                           eliminar(columna, fi);
                           if(salir)break;                           
                       }
                }           
        }
    }
      void buscarPareja2(int columna, int fi){
          for (int fila = 0; fila < 9; fila++) {
              if(Msudoku[fila][columna].getCandidatos().size() ==2 && fi!=fila){
                iguales=  compararParejas(Msudoku[fila][columna].getCandidatos(),Msudoku[fi][columna].getCandidatos());
                if(iguales){
                       posi2= fila;
                        pareja =Msudoku[fila][columna].getCandidatos();
                       
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
    void eliminar(int columna , int p2){ 
        boolean pintar=false;
            for (int fi = 0; fi < 9; fi++) {
                if(!Msudoku[fi][columna].isEncontrado()){
                    if((fi!=p2 && fi!=posi2)){                        
                        ArrayList<Integer> tem = Msudoku[fi][columna].getCandidatos();
                        for (int i = 0; i < tem.size(); i++) {
                            if(tem.get(i)==pareja.get(0) || tem.get(i)==pareja.get(1) ){
                                salir=true;
                                if(!pista){
                                    Msudoku[fi][columna].EliminarCandidato(tem.get(i));
                                    i--;
                                    if(jcbmCandidato.isSelected()) imprimirCandidatos(fi, columna);  
                                }
                                else{
                                    pintar=true;
                                    Msudoku[fi][columna].getJtf().setBackground(new Color(253,253,174));
                                    Msudoku[fi][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                    break;                                    
                                }
                                
                            }
                    }
                }
            }
        }
            if(pintar&&pista){
                color(p2, columna);
                color(posi2, columna);
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
