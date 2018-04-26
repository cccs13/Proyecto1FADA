

package Metodos.CuartetosDesnudos;

import Metodos.Sencillos.SencilloAlDescubierto;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.dificil;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;


public class CuartetosDesnudosColumna_2 {
    private boolean salir;
    private ArrayList<Integer> tem = new ArrayList<Integer>();
    private int beses;
    private ArrayList<Integer> NoExisten = new ArrayList<>();
    public void buscar(){
        salir=false;
        for (int columa = 0; columa < 9; columa++) {
            existeNNumeros(columa);
            buscarTrio(columa);
            if(salir)break;
        }
       if(salir && !pista) new SencilloAlDescubierto().buscarSencillo();
       else if(!salir){
           if(pista)info.setText("No hay pista disponible..");
           else info.setText("No hay solucion");
           info.setBackground(new Color(255,95,95));
       }
    }
    private void buscarTrio(int columna){        
        for (int fil = 0; fil < 9; fil++) {  
            tem.clear();
            tem =  (ArrayList<Integer>) Msudoku[fil][columna].getCandidatos().clone();
            if(!Msudoku[fil][columna].isEncontrado() && tem.size()==2){ 
                for (int i = 0; i < NoExisten.size(); i++) {  
                    if(completarTrio(NoExisten.get(i))){
                        for (int j = i; j < NoExisten.size(); j++){ 
                            beses=1;
                            if(completarTrio(NoExisten.get(j))){
                                quitareliminar(columna);
                                Msudoku[fil][columna].setElimianr(false);
                                existeTrio(columna, fil);
                                if(beses==4){
                                    eliminarCandidatos(columna);
                                    if(salir){                         
                                        fil=9;
                                        i=9;
                                        break;
                                    }
                                }
                                tem.remove(3);                        
                            }
                        }                                                
                        tem.remove(2);
                    }
                }
            }
        }                   
    }
    private void quitareliminar(int columna){
        for (int fila = 0; fila < 9; fila++) {
             Msudoku[fila][columna].setElimianr(true);
        }
    }
    private boolean completarTrio(int num){        
        for (int i = 0; i < tem.size(); i++) {
            if(tem.get(i)==num){                
               return false;
            }
        }
       tem.add(num);
       return true;       
    }
    private void existeNNumeros(int columna){
        NoExisten.clear();
        for (int i = 1; i <= 9; i++) {
           NoExisten.add(i); 
        }
        for (int fil = 0 ;fil < 9; fil++) {           
            if(Msudoku[fil][columna].isEncontrado()){
               NoExisten.remove(((Integer)Msudoku[fil][columna].getNumero()));
            }
        }
    }
    private void existeTrio(int columa, int fil){
        for (int fila = 0; fila < 9; fila++) {
            if(fila != fil){
                if(!Msudoku[fila][columa].isEncontrado() && Msudoku[fila][columa].getCandidatos().size() < 5){
                    if(iguales(Msudoku[fila][columa].getCandidatos())){
                        Msudoku[fila][columa].setElimianr(false);
                        beses++; 
                    }
                }
            }  
        }   
    }
    private boolean iguales( ArrayList<Integer> tri){
        boolean noIgual;
        for (int i = 0; i < tri.size(); i++) {
            noIgual= false;
            for (int j = 0; j < tem.size(); j++) {
                if(tri.get(i) == tem.get(j)){
                    noIgual = true;
                    break;                    
                }
            }
            if(!noIgual) return false; 
        }       
        return true;
    }
    private void eliminarCandidatos(int columna){
        boolean pintar=false;
        for (int fila = 0; fila < 9; fila++) {
            if(Msudoku[fila][columna].isElimianr() && !Msudoku[fila][columna].isEncontrado()) {
                ArrayList<Integer>aux =  Msudoku[fila][columna].getCandidatos();
                for (int i = 0; i < tem.size(); i++) {            
                    for (int j = 0; j < aux.size(); j++) {
                        if(tem.get(i) == aux.get(j)){
                            salir = true;
                            if(!pista){
                                Msudoku[fila][columna].EliminarCandidato((Integer)aux.get(j));
                                dificil=true;
                                j--;                               
                                if(jcbmCandidato.isSelected()){
                                    Msudoku[fila][columna].getJtf().setText(null);
                                    for (int h = 0; h < Msudoku[fila][columna].getCandidatos().size(); h++) {
                                        Msudoku[fila][columna].getJtf().append(" "+ Msudoku[fila][columna].getCandidatos().get(h));
                                    }
                                }
                            }
                            else{
                                Msudoku[fila][columna].getJtf().setBackground(new Color(253,253,174)); 
                                Msudoku[fila][columna].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                pintar =true;
                                i=9;
                                break;    
                            }
                        }                          
                    }
                }  
            } 
        }
        if(pintar && pista){
            pintar(columna);
            candi.setText("--");
            info.setText("Exclusión basada en Cuartetos desnudos");
        } 
    }
    private void pintar(int columna){
        for (int f = 0; f < 9; f++) {         
            if(!Msudoku[f][columna].isEncontrado() && !Msudoku[f][columna].isElimianr()){
                Msudoku[f][columna].getJtf().setBackground(new Color(69,196,84));
            } 
        }   
    } 
}
