//Proyecto Fada 

package Metodos.Crear;

import static Ventanas.Principal.jcbmCandidato;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;

public class FadaCandidatos {
    ArrayList <Integer> temNum =new ArrayList<>();    
    boolean estaCuadr;
    boolean estaFil;
    boolean estaCol;  
    public void PosibiDisp(){
        for (int i = 0; i < 3; i++) {//Recorremos todos los subCuadros que existen 9
            for (int j = 0; j < 3; j++) {
                buscarCandidatosiDisponibles(i,j);
            }
        }
    }
    private void buscarCandidatosiDisponibles(int posiFil, int posiCol){
        temNum.clear();
        extraerNumFijosSubcuadro(posiFil, posiCol);        
        for (int numero = 1; numero <= 9; numero++) {// numeros posibles        
            for (int k = posiFil*3; k < (posiFil*3)+3; k++) {
                for (int i = posiCol*3; i < (posiCol*3)+3; i++) {
                    if(!MatrizS[k][i].isEncontrado()){
                        estaCuadr = estaNumeroEnSubCuadro(numero);                   
                        if(!estaCuadr){
                            estaFil = existeNumeroFila(k, numero);                      
                            if(!estaFil){
                                estaCol = existeNumeroColumna(i, numero);
                                if(!estaCol){                                     
                                    MatrizS[k][i].addNunm(numero);
                                    if(jcbmCandidato.isSelected())
                                        MatrizS[k][i].getJtf().append(" "+numero);//   
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void extraerNumFijosSubcuadro(int posiFil, int posiCol){
      for (int fil = posiFil*3; fil <(posiFil*3+3); fil++) {// extraemos los números de cada subCuadro
            for (int col = posiCol*3; col <posiCol*3+3; col++) {
                if(MatrizS[fil][col].isEncontrado()){
                    temNum.add(MatrizS[fil][col].getNumero());
               } 
            }
        }   
    }
    private boolean estaNumeroEnSubCuadro(int num){// Miramos si esta en el subCuadro
        for (int i = 0; i < temNum.size(); i++) {
           if(num == temNum.get(i)){
               return true;
           } 
        }
        return false;
    }
    private boolean existeNumeroFila(int fil, int num){ // Miramos si existe en la fila      
        for (int col =0; col < 9; col++) {
              if( MatrizS[fil][col].getNumero()==num){
                 return true; 
              }
        }
        return false;
    }
    private boolean existeNumeroColumna(int col, int num){ // Semira si existe en la columna
       for (int fil = 0; fil < 9; fil++) {
              if( MatrizS[fil][col].getNumero()==num){
                 return true; 
              }
        }
        return false;        
    }
}
