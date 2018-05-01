
package Metodos.Fada1;

import Metodos.Fada2.FadaPDCua;
import static Ventanas.Principal.celdaComplet;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jLabel2;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


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
                         if(!MatrizS[k][h].isEncontrado()){
                         temp = MatrizS[k][h].getCandidatos();
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
                        MatrizS[psiF][psiC].setNumero(numOcul);
                        MatrizS[psiF][psiC].setEncontrado(true);
                             MatrizS[psiF][psiC].getJtf().setFont(new java.awt.Font("Monospaced", 1, 24));                            
                             MatrizS[psiF][psiC].getJtf().setText(" "+numOcul);
                              
                         MatrizS[psiF][psiC].getCandidatos().clear();
                         MatrizS[psiF][psiC].addNunm(numOcul);

                         eliminarNumCol(psiC, numOcul);
                         eliminarNumFil(psiF, numOcul);
                         eliminarCandidatoCuadro(psiF, psiC, numOcul);                     
                         celdaComplet++;
                             MatrizS[psiF][psiC].getJtf().setBackground(new Color(217,254,217));
                             jLabel2.setText("Celda Completadas "+celdaComplet);                  
                             
                     }                     
                     else { 
                        MatrizS[psiF][psiC].getJtf().setBackground(new Color(253,253,174));
                         MatrizS[psiF][psiC].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
                    if(MatrizS[k][i].isEncontrado()){
                        noBuscar.add(MatrizS[k][i].getNumero());
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
           tem = MatrizS[fil][i].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j) ){
                   MatrizS[fil][i].EliminarCandidato( (Integer) tem.get(j));
                    if(jcbmCandidato.isSelected()&& !Ventanas.Principal.nevoJuego)imprimirPosibilidades(fil, i);
                }
            }
        }
    }
        
    public void eliminarNumCol(int col, int num){
       ArrayList tem = null;
        for (int i =0; i < 9; i++) {
           tem = MatrizS[i][col].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)){
                   MatrizS[i][col].EliminarCandidato((Integer) tem.get(j));
                    if(jcbmCandidato.isSelected()&&!Ventanas.Principal.nevoJuego)imprimirPosibilidades(i, col);
                }
            }
        }
    }
    void eliminarCandidatoCuadro(int fila, int columna, int num){
       for (int f = ((int) fila/3)*3; f <((int) fila/3)*3+3; f++) {
           for (int c =((int) columna/3)*3; c <((int) columna/3)*3+3; c++) {
               for (int i = 0; i < MatrizS[f][c].getCandidatos().size(); i++) {
                    if(num == MatrizS[f][c].getCandidatos().get(i)){
                        MatrizS[f][c].EliminarCandidato((Integer)MatrizS[f][c].getCandidatos().get(i));
                                                   
                            if(jcbmCandidato.isSelected() && !Ventanas.Principal.nevoJuego){
                                MatrizS[f][c].getJtf().setText(null);
                                
                                for (int h = 0; h < MatrizS[f][c].getCandidatos().size(); h++) {
                                    MatrizS[f][c].getJtf().append(" "+ MatrizS[f][c].getCandidatos().get(h));
                                }
                            }
                        
                    }
               }
           }
       }
   }
    public void imprimirPosibilidades(int fil, int col){
       if(!MatrizS[fil][col].isEncontrado())
           MatrizS[fil][col].getJtf().setText(null);
        for (int i = 0; i <   MatrizS[fil][col].getCandidatos().size(); i++) {
            if(!MatrizS[fil][col].isEncontrado())
                MatrizS[fil][col].getJtf().append(" "+MatrizS[fil][col].getCandidatos().get(i));
        }
    }
}