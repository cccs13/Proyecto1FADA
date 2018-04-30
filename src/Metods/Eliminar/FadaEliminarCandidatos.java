

package Metods.Eliminar;

import Sudoku.FadaSudokuSecundario;
import java.util.ArrayList;


public class FadaEliminarCandidatos {
    
    public static void eliminarFila(int num, int fila, FadaSudokuSecundario m[][]){     
        ArrayList tem = null;
        for (int c = 0; c < 9; c++) {
            tem = m[fila][c].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j)&& !m[fila][c].isEncontrado()){                  
                   m[fila][c].EliminarCandidato((Integer) tem.get(j));
                   break;
                }
            }
        }
    }
   public static void eliminarColumna(int num, int columna, FadaSudokuSecundario m[][]){
        ArrayList tem = null;
        for (int f =0; f < 9; f++) {
            tem = m[f][columna].getCandidatos();
            for (int j = 0; j < tem.size(); j++) {
                if(num == (int)tem.get(j) && !m[f][columna].isEncontrado()){                     
                    m[f][columna].EliminarCandidato((Integer) tem.get(j));                    
                    break;
                }
            }
        }
   }
    public static void eliminarRegion(int num, int fila,int columna, FadaSudokuSecundario m[][]){
        for (int f = (fila/3)*3; f < (fila/3)*3+3; f++) {
            for (int c = (columna/3)*3; c < (columna/3)*3+3; c++) {
                if(!m[f][c].isEncontrado()){
                    ArrayList<Integer>tem ;
                    tem = m[f][c].getCandidatos();
                    for (int i = 0; i < tem.size(); i++) {
                        if(tem.get(i)== num){                            
                            m[f][c].EliminarCandidato((Integer) tem.get(i)); 
                          break;
                        }
                    }
                }
            }
        }
    }
}
