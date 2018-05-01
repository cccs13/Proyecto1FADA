package Metodos.Fada2;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class FadaPDCua {
    int parejas =1;
    int posi1,posi2;
    boolean iguales, salir;
    ArrayList<Integer>pareja;
   public  void parejas(){
       salir = false;     
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                buscarParejasCuadro_1(fila, columna);
                if(salir){
                    fila=9;
                    break;
                }
            } 
        }
        if(salir && !pista) new Fada1D().buscarSencillo();
        else if(!salir) new FadaPDF().parejas();
    }
    void buscarParejasCuadro_1(int fila, int columnas){
        for (int fi = fila*3; fi < (fila*3)+3; fi++) {
            for (int co = columnas*3; co < (columnas*3)+3; co++) {
                if(MatrizS[fi][co].getCandidatos().size() ==2){
                    parejas = 1;
                    iguales = false;
                     buscarParejaCuadro_2(fila, columnas,  fi, co );
                     if(parejas==2 && iguales){
                         eliminar(fila, columnas, fi, co, posi1, posi2);                        
                     }   
                     if(salir)break;
                }
            }
        }    
    }    
    void buscarParejaCuadro_2(int fila, int columnas, int pp1,int pp2){       
       for (int fi = fila*3; fi < (fila*3)+3; fi++) {
            for (int co = (columnas*3); co < (columnas*3)+3; co++) {               
                if(MatrizS[fi][co].getCandidatos().size() == 2 ){
                    if((pp1!=fi || pp2!=co)){
                        iguales = compararParejas(MatrizS[pp1][pp2].getCandidatos(), MatrizS[fi][co].getCandidatos());
                        if(iguales){
                           posi1 = fi;
                           posi2 = co;
                          pareja = MatrizS[fi][co].getCandidatos();                       
                        }
                    }
                }
            }
        } 
    }    
    boolean compararParejas(ArrayList<Integer> pareja1,ArrayList<Integer> pareja2){        
       if(pareja1.get(0) == pareja2.get(0) || pareja1.get(0) == pareja2.get(1)){
           if(pareja1.get(1) == pareja2.get(0) || pareja1.get(1) == pareja2.get(1)){
                 parejas++;
                 return true;
            }
       }
        return false;
    }
    void eliminar(int fila , int columnas, int p1, int p2, int p3, int p4){
         for (int fi = fila*3; fi < (fila*3)+3; fi++) {
            for (int co = columnas*3; co < (columnas*3)+3; co++) {
                if(!MatrizS[fi][co].isEncontrado()){
                    if((fi==p1 && co==p2) || (fi==p3 && co==p4) ){}  
                    else{
                        ArrayList<Integer> tem = MatrizS[fi][co].getCandidatos();
                        for (int i = 0; i < tem.size(); i++) {
                            if(tem.get(i)==pareja.get(0) || tem.get(i)==pareja.get(1) ){
                                salir = true;
                                if(!pista){
                                  MatrizS[fi][co].EliminarCandidato(tem.get(i));
                                  i=0;
                                  if(jcbmCandidato.isSelected())imprimirCandidatos(fi, co);
                                }
                                else {
                                    MatrizS[fi][co].getJtf().setBackground(new Color(253,253,174));
                                    MatrizS[fi][co].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                    color(p1, p2);
                                    color(p3, p4);
                                    candi.setText("--");
                                    info.setText("Exclusión basada en Parejas Desnudas");
                                    break;
                                }
                                
                            }
                             
                        }
                    }
                }
            }
        }
    }
    void color(int fil, int col){
       MatrizS[fil][col].getJtf().setBackground(new Color(69,196,84));
    }
    public void imprimirCandidatos(int fil, int col){
           if(!MatrizS[fil][col].isEncontrado())
               MatrizS[fil][col].getJtf().setText(null);
            for (int i = 0; i <   MatrizS[fil][col].getCandidatos().size(); i++) {
                if(!MatrizS[fil][col].isEncontrado())
                    MatrizS[fil][col].getJtf().append(" "+MatrizS[fil][col].getCandidatos().get(i));            
        }           
    }
}