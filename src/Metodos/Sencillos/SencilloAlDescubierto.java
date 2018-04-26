/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodos.Sencillos;


import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.celdaComplet;
import static Ventanas.Principal.cronometro;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jLabel2;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class SencilloAlDescubierto {   
   public  void buscarSencillo(){ 
       boolean tem=false;
        for (int fil = 0; fil < 9; fil++) {
            for (int col = 0; col < 9; col++) {
                if( !Msudoku[fil][col].isEncontrado() && Msudoku[fil][col].getCandidatos().size() == 1){
                   int n =Msudoku[fil][col].getCandidatos().get(0);
                    if(!pista){                     
                     Msudoku[fil][col].setNumero(n);
                     Msudoku[fil][col].setEncontrado(true);
//                     if(!Ventanas.Principal.nevoJuego){
                         Msudoku[fil][col].getJtf().setFont(new java.awt.Font("Monospaced", 1, 24));                        
                         Msudoku[fil][col].getJtf().setBackground(new Color(217,254,217));
//                     }
                     eliminarCandidatoCol(col, n);
                     eliminarCandidatoFil(fil, n);
                     eliminarCandidatoCuadro(fil, col, n);
//                    if(!Ventanas.Principal.nevoJuego) 
                        Msudoku[fil][col].getJtf().setText(" "+n);
                     fil=0;
                     col=0;
                     celdaComplet++;                     
                   }
                    else{
                        Msudoku[fil][col].getJtf().setBackground(new Color(253,253,174));
                         Msudoku[fil][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                       tem=true;
                       fil=9;
                       info.setText("Sencillo al descubierto");                       
                       candi.setText(""+n);
                       break;                       
                   }                     
                }
            }
        }
        jLabel2.setText("Celdas Completadas "+celdaComplet);
        if(celdaComplet < 81 && !tem) new SencilloOculto().buscarSencilloOculto();
        else if(celdaComplet == 81){ 
            cronometro.suspender();
            info.setText("FIN DE JUEGO...");
            info.setBackground(new Color(86,250,118));
        }
        
    }
   void eliminarCandidatoCuadro(int fila, int columna, int num){
        for (int f = ((int) fila/3)*3; f <((int) fila/3)*3+3; f++) {
            for (int c =((int) columna/3)*3; c <((int) columna/3)*3+3; c++) {
                for (int i = 0; i < Msudoku[f][c].getCandidatos().size(); i++) {
                    if(num == Msudoku[f][c].getCandidatos().get(i)){
                        Msudoku[f][c].EliminarCandidato((Integer)Msudoku[f][c].getCandidatos().get(i));
                        if(jcbmCandidato.isSelected()){                           
                            if(jcbmCandidato.isSelected() &&(!Ventanas.Principal.nevoJuego)){
                                Msudoku[f][c].getJtf().setText(null);
                                for (int h = 0; h < Msudoku[f][c].getCandidatos().size(); h++) {
                                    Msudoku[f][c].getJtf().append(" "+ Msudoku[f][c].getCandidatos().get(h));
                                }
                            }
                       }
                   }
               }
           }
       }
   }
    void eliminarCandidatoFil(int fil, int num){
        ArrayList tem = null;
        for (int i =0; i < 9; i++) {
            tem = Msudoku[fil][i].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)){
                   Msudoku[fil][i].EliminarCandidato((Integer) tem.get(j));
                    if(jcbmCandidato.isSelected() && (!Ventanas.Principal.nevoJuego) ){
                        imprimirCandidatos(fil, i);                        
                    }
                }
            }
        }
    }       
    public void eliminarCandidatoCol(int col, int num){
       ArrayList tem = null;
        for (int i =0; i < 9; i++) {
            tem = Msudoku[i][col].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)){
                   Msudoku[i][col].EliminarCandidato((Integer) tem.get(j));
                    if(jcbmCandidato.isSelected() && (!Ventanas.Principal.nevoJuego))imprimirCandidatos(i, col);
                }
            }
        }
    }
    public void imprimirCandidatos(int fil, int col){
           if(!Msudoku[fil][col].isEncontrado())
               Msudoku[fil][col].getJtf().setText(null);
            for (int i = 0; i <   Msudoku[fil][col].getCandidatos().size(); i++) {
                Msudoku[fil][col].getJtf().append(" "+Msudoku[fil][col].getCandidatos().get(i));            
        }           
    }
    
}
