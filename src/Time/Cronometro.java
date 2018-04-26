

package Time;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class Cronometro extends Thread{  
    private int segundos;
    private int minutos;
    private int hora;
    private Thread cronometro;
    private JLabel jlbCronometro;
    
    public Cronometro( JLabel jlbCronometro) {
        this.segundos = 0;
        this.minutos = 0;
        this.hora = 0;
        this.jlbCronometro = jlbCronometro;
        this.cronometro = new Thread(this);
        this.cronometro.start();
    }
    public void run(){
        while(true){
            try { 
                segundos++;               
                cronometro.sleep(1000) ;                
                if(segundos == 60){
                   segundos=0;
                   minutos++;               
                }
                if(minutos ==60){
                   hora++;
                  minutos=0;
                }
                jlbCronometro.setText(" "+hora+":"+minutos+":"+segundos);
            
          } catch (InterruptedException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        }
        
    }

    
    public int getSegundos() {
        return segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getHora() {
        return hora;
    }

    public Thread getCronometro() {
        return cronometro;
    }

    public JLabel getJlbCronometro() {
        return jlbCronometro;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setCronometro(Thread cronometro) {
        this.cronometro = cronometro;
    }

    public void setJlbCronometro(JLabel jlbCronometro) {
        this.jlbCronometro = jlbCronometro;
    }
    
    
    public void stopCronometro(){
        cronometro.stop();
    }
    public void suspender(){
       cronometro.suspend();
    }
    public void resumen(){
          cronometro.resume();
    }
    public void detenerHilo(){
        cronometro.stop();
    } 
  
}
