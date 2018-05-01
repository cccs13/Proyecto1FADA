

package Metodos.Fada1;


import static Ventanas.Principal.celdaComplet;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jLabel2;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class Fada1D {   
   public  void buscarSencillo(){ 
       boolean tem=false;
        for (int fil = 0; fil < 9; fil++) {
            for (int col = 0; col < 9; col++) {
                if( !MatrizS[fil][col].isEncontrado() && MatrizS[fil][col].getCandidatos().size() == 1){
                   int n =MatrizS[fil][col].getCandidatos().get(0);
                    if(!pista){                     
                     MatrizS[fil][col].setNumero(n);
                     MatrizS[fil][col].setEncontrado(true);
                         MatrizS[fil][col].getJtf().setFont(new java.awt.Font("Monospaced", 1, 24));                        
                         MatrizS[fil][col].getJtf().setBackground(new Color(217,254,217));
                     eliminarCandidatoCol(col, n);
                     eliminarCandidatoFil(fil, n);
                     eliminarCandidatoCuadro(fil, col, n);
                        MatrizS[fil][col].getJtf().setText(" "+n);
                     fil=0;
                     col=0;
                     celdaComplet++;                     
                   }
                    else{
                        MatrizS[fil][col].getJtf().setBackground(new Color(253,253,174));
                         MatrizS[fil][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
        if(celdaComplet < 81 && !tem) new Fada1O().buscarSencilloOculto();
        else if(celdaComplet == 81){ 

            info.setText("FIN DE JUEGO...");
            info.setBackground(new Color(86,250,118));
        }
        
    }
   void eliminarCandidatoCuadro(int fila, int columna, int num){
        for (int f = ((int) fila/3)*3; f <((int) fila/3)*3+3; f++) {
            for (int c =((int) columna/3)*3; c <((int) columna/3)*3+3; c++) {
                for (int i = 0; i < MatrizS[f][c].getCandidatos().size(); i++) {
                    if(num == MatrizS[f][c].getCandidatos().get(i)){
                        MatrizS[f][c].EliminarCandidato((Integer)MatrizS[f][c].getCandidatos().get(i));
                        if(jcbmCandidato.isSelected()){                           
                            if(jcbmCandidato.isSelected() &&(!Ventanas.Principal.nevoJuego)){
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
   }
    void eliminarCandidatoFil(int fil, int num){
        ArrayList tem = null;
        for (int i =0; i < 9; i++) {
            tem = MatrizS[fil][i].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)){
                   MatrizS[fil][i].EliminarCandidato((Integer) tem.get(j));
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
            tem = MatrizS[i][col].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)){
                   MatrizS[i][col].EliminarCandidato((Integer) tem.get(j));
                    if(jcbmCandidato.isSelected() && (!Ventanas.Principal.nevoJuego))imprimirCandidatos(i, col);
                }
            }
        }
    }
    public void imprimirCandidatos(int fil, int col){
           if(!MatrizS[fil][col].isEncontrado())
               MatrizS[fil][col].getJtf().setText(null);
            for (int i = 0; i <   MatrizS[fil][col].getCandidatos().size(); i++) {
                MatrizS[fil][col].getJtf().append(" "+MatrizS[fil][col].getCandidatos().get(i));            
        }           
    }
    
}
