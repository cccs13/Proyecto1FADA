

package Metodos.Fada4;

import Metodos.Fada1.Fada1D;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class FadaCDCua3 {
     ArrayList<Integer> tem = new ArrayList<>();
    int bese;
    boolean salir=false;
     ArrayList<Integer> NoExisten = new ArrayList<>();
    public void buscar(){
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {                
                existeNNumeros(fila, columna);
                buscarTrio(fila, columna);
                if(salir){
                   fila=9;
                   break;
                }
            }
        }
        if(salir && !pista)new Fada1D().buscarSencillo();
        else if(!salir) new FadaCDF3().buscar();
    }
    public void buscarTrio(int fila, int columna){
        for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {
                tem.clear();
                tem = (ArrayList<Integer>) MatrizS[fil][col].getCandidatos().clone();
                if(!MatrizS[fil][col].isEncontrado() && tem.size() == 3){                                       
                    for (int i = 0; i < NoExisten.size(); i++) {
                        bese=1; 
                        if(completarTrio(NoExisten.get(i))){
                            quitarEliminar(fila, columna);
                            MatrizS[fil][col].setElimianr(false);
                            existeTrio(fila, columna, fil, col);
                            if(bese==4){
                              eliminar(fila, columna); 
                              if(salir){
                                fil=9;
                                col=9;
                                break;
                             }
                           }
                           tem.remove((int)3);
                        }                        
                    }
                }
            }
        }
    }
    boolean completarTrio(int num){        
        for (int i = 0; i < tem.size(); i++) {
            if(tem.get(i)==num){                
               return false;
            }
        }
       tem.add(num);
       return true;       
    }
    void existeNNumeros(int fila, int columna){
        NoExisten.clear();
        for (int i = 1; i <= 9; i++) {
           NoExisten.add(i);
        }
         for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {
                if(MatrizS[fil][col].isEncontrado()){
                   NoExisten.remove(((Integer)MatrizS[fil][col].getNumero()));
                }
            }
         }
    }    
    public void existeTrio(int fila, int columna, int fil, int col){
        for (int f =fila*3; f <fila*3+3 ; f++) {
            for (int c = columna*3 ;c < (columna*3)+3; c++) {
                if(f!=fil || c!=col){
                    if(!MatrizS[f][c].isEncontrado() && MatrizS[f][c].getCandidatos().size()<5 ){
                        if(iguales(MatrizS[f][c].getCandidatos())){
                            bese++;  
                            MatrizS[f][c].setElimianr(false);
                        }
                    }                    
                }
            }
        }
    }
    private boolean iguales( ArrayList<Integer> cuar){
        boolean noIgual;
        for (int i = 0; i < cuar.size(); i++) {
            noIgual= false;
            for (int j = 0; j < tem.size(); j++) {
                if(cuar.get(i) == tem.get(j)){
                    noIgual = true;
                    break;                    
                }
            }
           if(!noIgual) return false; 
        }
       
        return true;
    }
    private void quitarEliminar(int fila, int columna){
       for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {
              MatrizS[fil][col].setElimianr(true);
            } 
       }
    }
     public void eliminar(int fila , int columna){
        boolean pintar=false;
        for (int fil = fila*3; fil < fila*3+3; fil++) {
            for (int col = columna*3; col < columna*3+3; col++) {
                if(!MatrizS[fil][col].isEncontrado() && MatrizS[fil][col].isElimianr()){
                    ArrayList<Integer>aux =  MatrizS[fil][col].getCandidatos();
                   for (int i = 0; i < tem.size(); i++) {            
                      for (int j = 0; j < aux.size(); j++) {
                          if(tem.get(i) == aux.get(j)){
                            salir=true;
                            if(!pista) {
                                dificil=true;
                                MatrizS[fil][col].EliminarCandidato((Integer)aux.get(j));
                                if(jcbmCandidato.isSelected()){
                                    MatrizS[fil][col].getJtf().setText(null);
                                    for (int h = 0; h < MatrizS[fil][col].getCandidatos().size(); h++) {
                                        MatrizS[fil][col].getJtf().append(" "+ MatrizS[fil][col].getCandidatos().get(h));
                                    }
                                }
                                j--;
                            }
                            else{
                                MatrizS[fil][col].getJtf().setBackground(new Color(253,253,174)); 
                                MatrizS[fil][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                pintar=true;
                                i=9;
                                break; 
                            }
                          }
                      }
                   }
                }
            }
        }
        if(pintar && pista){
            pintar(fila, columna);
            candi.setText("--");
            info.setText("Exclusión basada en Cuartetos desnudos");
        }
    }
    private void pintar(int fila, int columna){
     for (int f = fila*3; f <fila*3+3; f++) {
         for (int c = columna*3; c < columna*3+3; c++) {
            if(!MatrizS[f][c].isEncontrado() && !MatrizS[f][c].isElimianr()){
                MatrizS[f][c].getJtf().setBackground(new Color(69,196,84));
            } 
         }
   
     }
   
 }
}
