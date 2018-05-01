//proyecto fada

package Metodos.FadaCB;

import Metodos.Fada1.Fada1O;
import static Ventanas.Principal.candi;
import static Ventanas.Principal.info;
import static Ventanas.Principal.jcbmCandidato;
import static Ventanas.Principal.pista;
import java.awt.Color;
import java.util.ArrayList;
import static Ventanas.Principal.MatrizS;


public class FadaCanBlo1C {
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
       if(salir && ! pista) new Fada1O().buscarSencilloOculto();
       else if(!salir)new FadaCanBlo1F().buscar();      
            
    }   
    public void buscarCandidatos(int fila, int columna){        
            for (int col = columna*3; col < columna*3+3; col++) {
                serepite(fila, col);
                if(salir)break;
            }
    }
    public void serepite(int fila, int columas){
        for (int f = fila*3; f < fila*3+3; f++) {            
            if(!MatrizS[f][columas].isEncontrado()){
                tem = MatrizS[f][columas].getCandidatos();
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
            if(ff!=f &&  !MatrizS[ff][columna].isEncontrado()){
                ArrayList<Integer> aux = MatrizS[ff][columna].getCandidatos();
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
               if(idColum !=j && !MatrizS[i][j].isEncontrado()) {
                    ArrayList<Integer> aux = MatrizS[i][j].getCandidatos();
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
               tem = MatrizS[i][col].getCandidatos();
               if((i<fila*3 || i>(fila*3+2))){
                   if(!MatrizS[i][col].isEncontrado() ){
                        for (int j = 0; j < tem.size(); j++) {
                             if(num == (int)tem.get(j)){                                
                                if(!pista){
//                                    Ventanas.Principal.dificil=true;
                                    MatrizS[i][col].EliminarCandidato((Integer) tem.get(j));                                                                       
                                    if(jcbmCandidato.isSelected())imprimirPosibilidades(i, col); 
                                    
                                }
                                else{                                    
                                    MatrizS[i][col].getJtf().setBackground(new Color(253,253,174));
                                    MatrizS[i][col].getJtf().setBorder(javax.swing.BorderFactory.createLineBorder(Color.GREEN,2));
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
            info.setText("Candidato Bloqueado (1)");
        }
    }
    void color(int fila, int columna){
        for (int f = fila*3; f < fila*3+3; f++) {
            for (int c = columna*3; c < columna*3+3; c++) {
               MatrizS[f][c].getJtf().setBackground(new Color(69,196,84));
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
