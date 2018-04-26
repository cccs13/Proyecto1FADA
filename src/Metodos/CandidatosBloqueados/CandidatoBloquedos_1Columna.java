

package Metodos.CandidatosBloqueados;

import Metodos.Sencillos.SencilloOculto;
import static Ventanas.Principal.Msudoku;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class CandidatoBloquedos_1Columna {
    int nBeses;
    boolean estaRepetido;
    int idColum;
    ArrayList <Integer>tem;
    int colum;
    boolean salir;
   public  void buscar(){
       salir =false;
       for (int fila = 0; fila < 3; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                colum=columna;
                buscarCandidatos(fila, columna);
                if(salir)break;
                } 
            if(salir)break;            
            }
       if(salir && ! pista) new SencilloOculto().buscarSencilloOculto();
       else if(!salir)new CandidatosBloquedos_1Fila().buscar();      
            
    }   
    public void buscarCandidatos(int fila, int columna){        
            for (int col = columna*3; col < columna*3+3; col++) {
                serepite(fila, col);
                if(salir)break;
            }
    }
    public void serepite(int fila, int columas){
        for (int f = fila*3; f < fila*3+3; f++) {            
            if(!Msudoku[f][columas].isEncontrado()){
                tem = Msudoku[f][columas].getCandidatos();
                idColum = columas;                
                for (int i = 0; i < tem.size(); i++) {
                    nBeses=1;
                    comparar(fila, columas, f, tem.get(i));
                    if(nBeses>=2 && !existeResCuadro(fila, columas,  tem.get(i) ) && !salir){
                        eliminarNumCol(fila, columas, tem.get(i));
                    }
                    if(salir)break;                    
                }
            }
            if(salir==true)break;
        }
    }
    public void comparar(int fila, int columna,int f, int candidato){
        for (int ff = fila*3; ff < fila*3+3; ff++) {
            if(ff!=f &&  !Msudoku[ff][columna].isEncontrado()){
                ArrayList<Integer> aux = Msudoku[ff][columna].getCandidatos();
                for (int i = 0; i < aux.size(); i++) {
                    if(aux.get(i) == candidato){
                       nBeses++;  
//                        System.out.println("Comparar");
                    }
                }
//               
            }
        }
    }
    public boolean existeResCuadro(int fila,int columna, int candi){
        for (int i = fila*3; i < fila*3+3; i++) {
            for (int j = colum*3; j < colum*3+3; j++) {
               if(idColum !=j && !Msudoku[i][j].isEncontrado()) {
                    ArrayList<Integer> aux = Msudoku[i][j].getCandidatos();
                    for (int g = 0; g < aux.size(); g++) {
                    if(candi == aux.get(g)){ 
                        return true;
                    }
                }
               }
            }
        }
        return false;          
    }
    public void eliminarNumCol(int fila,int col, int num){
       ArrayList tem = null;
       boolean pintar=false;
        for (int i =0; i < 9; i++) {
               tem = Msudoku[i][col].getCandidatos();
               if((i<fila*3 || i>(fila*3+2))){
                   if(!Msudoku[i][col].isEncontrado() ){
                        for (int j = 0; j < tem.size(); j++) {
                             if(num == (int)tem.get(j)){                                
                                if(!pista){
//                                    Ventanas.Principal.dificil=true;
                                    Msudoku[i][col].EliminarCandidato((Integer) tem.get(j));                                                                       
                                    if(jcbmCandidato.isSelected())imprimirPosibilidades(i, col); 
                                    
                                }
                                else{                                    
                                    Msudoku[i][col].getJtf().setBackground(new Color(253,253,174));
                                    Msudoku[i][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
                                    pintar=true;                                    
                                }                               
                                salir=true;
                                break;                                
                             }
                        }
                   }
               }         
        }
        if(pintar && pista){
            color(fila, colum);
            candi.setText(""+num);
            info.setText("Candidato Bloquiedo (1)");
        }
    }
    void color(int fila, int columna){
        for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
               Msudoku[f][c].getJtf().setBackground(new Color(69,196,84));
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
