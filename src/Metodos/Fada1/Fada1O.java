
package Metodos.Fada1;

import Metodos.Fada2.FadaPDCua;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.celdaComplet;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jLabel2;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class Fada1O {
    ArrayList <Integer>  noBuscar = new ArrayList<>();    
    ArrayList <Integer> temp; 
    int psiF;
    int psiC;
    int bese;
    boolean salir;
    public void buscarSencilloOculto(){
        salir=false;
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                sencillo(fila, columna);
                if(salir){
                    fila=9;
                    break;
                }
            }
 
        }
        if(salir && !pista)   new Fada1D().buscarSencillo();
        else if(!salir) new FadaPDCua().parejas();
    }
    void sencillo(int posiFil, int posiCol){        
        numerosExistentes(posiFil, posiCol);        
         for (int numOcul = 1; numOcul <= 9; numOcul++) {           
             bese=0;
             if(!existeNumero(numOcul)){
                 for (int k = posiFil*3; k < (posiFil*3)+3; k++) {
                     for (int h = posiCol*3; h < (posiCol*3)+3; h++) {
                         if(!Msudoku[k][h].isEncontrado()){
                         temp = Msudoku[k][h].getCandidatos();
                         for (int j = 0; j < temp.size(); j++) {
                             if(temp.get(j) == numOcul){  
                                psiF=k;
                                psiC=h;
                                bese++;                          
                             }
                         }                         
                     } 
                     }
                }
                if(bese>1)   noBuscar.add(numOcul);
                if(bese==1){
                     if(!pista){
                        Msudoku[psiF][psiC].setNumero(numOcul);
                        Msudoku[psiF][psiC].setEncontrado(true);
//                        if(!Ventanas.Principal.nevoJuego){
                             Msudoku[psiF][psiC].getJtf().setFont(new java.awt.Font("Monospaced", 1, 24));                            
                             Msudoku[psiF][psiC].getJtf().setText(" "+numOcul);
                              
//                        }
                         Msudoku[psiF][psiC].getCandidatos().clear();
                         Msudoku[psiF][psiC].addNunm(numOcul);

                         eliminarNumCol(psiC, numOcul);
                         eliminarNumFil(psiF, numOcul);
                         eliminarCandidatoCuadro(psiF, psiC, numOcul);                     
                         celdaComplet++;
//                         if(!Ventanas.Principal.nevoJuego){
                             Msudoku[psiF][psiC].getJtf().setBackground(new Color(217,254,217));
                             jLabel2.setText("Celda Completadas "+celdaComplet);                  
                             
//                         }
                     }                     
                     else { 
                        Msudoku[psiF][psiC].getJtf().setBackground(new Color(253,253,174));
                         Msudoku[psiF][psiC].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                         info.setText("Sencillo Oculto");
                        candi.setText(""+numOcul);
                         
                     }
                     salir=true;
                     break;   
                     
                 }                 
             }
         }
    }
    void numerosExistentes(int posiFil, int posiCol){
        noBuscar.clear();// contiene los numeros que estan fijos 
        for (int k = posiFil*3; k < (posiFil*3)+3; k++) {
                for (int i = posiCol*3; i < (posiCol*3)+3; i++) {
                    if(Msudoku[k][i].isEncontrado()){
                        noBuscar.add(Msudoku[k][i].getNumero());
                    }
                }
        }      
    }
    boolean existeNumero(int num){
        for (int i = 0; i < noBuscar.size(); i++) {
            if((int)noBuscar.get(i )==num){
                return true;
            }
        }
        return false;  
    }
    void eliminarNumFil(int fil, int num){
        ArrayList tem = null;
        for (int i =0; i < 9; i++) {
           tem = Msudoku[fil][i].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j) ){
                   Msudoku[fil][i].EliminarCandidato( (Integer) tem.get(j));
                    if(jcbmCandidato.isSelected()&& !Ventanas.Principal.nevoJuego)imprimirPosibilidades(fil, i);
                }
            }
        }
    }
        
    public void eliminarNumCol(int col, int num){
       ArrayList tem = null;
        for (int i =0; i < 9; i++) {
           tem = Msudoku[i][col].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)){
                   Msudoku[i][col].EliminarCandidato((Integer) tem.get(j));
                    if(jcbmCandidato.isSelected()&&!Ventanas.Principal.nevoJuego)imprimirPosibilidades(i, col);
                }
            }
        }
    }
    void eliminarCandidatoCuadro(int fila, int columna, int num){
       for (int f = ((int) fila/3)*3; f <((int) fila/3)*3+3; f++) {
           for (int c =((int) columna/3)*3; c <((int) columna/3)*3+3; c++) {
               for (int i = 0; i < Msudoku[f][c].getCandidatos().size(); i++) {
                    if(num == Msudoku[f][c].getCandidatos().get(i)){
                        Msudoku[f][c].EliminarCandidato((Integer)Msudoku[f][c].getCandidatos().get(i));
                                                   
                            if(jcbmCandidato.isSelected() && !Ventanas.Principal.nevoJuego){
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
    public void imprimirPosibilidades(int fil, int col){
       if(!Msudoku[fil][col].isEncontrado())
           Msudoku[fil][col].getJtf().setText(null);
        for (int i = 0; i <   Msudoku[fil][col].getCandidatos().size(); i++) {
            if(!Msudoku[fil][col].isEncontrado())
                Msudoku[fil][col].getJtf().append(" "+Msudoku[fil][col].getCandidatos().get(i));
        }
    }
}