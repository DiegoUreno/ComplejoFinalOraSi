/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejo;
import javax.swing.*;
import java.awt.*;
import complejo.Plano;
import complejo.Calculadora;
import java.util.ArrayList;
/**
 *
 * @author Pc
 */
public class Complejo  {
    public static Plano plano;

    private double parteReal;
    private double parteImaginaria;
    private double r;
    private double theta;

    public Complejo() {
        this.parteReal = 0;
        this.parteImaginaria = 0;
    }

    public Complejo(double real, double imaginaria) {
        this.parteReal = real;
        this.parteImaginaria = imaginaria;
    }

    public double getParteReal() {
        return parteReal;
    }

    public double getParteImaginaria() {
        return parteImaginaria;
    }

    public double theta(){
        
            
            if (getParteReal() == 0 && getParteImaginaria() == 0) {
            this.theta = 0;
        } else if (getParteReal() == 0 && getParteImaginaria() != 0) {
            if (getParteImaginaria() > 0) {
                this.theta = 90;
            } else {
                this.theta = 270;
            }
        } else if (getParteReal() != 0 && getParteImaginaria() == 0) {
            if (getParteReal() > 0) {
                this.theta = 0;
            } else {
                this.theta = 180;
            }
        } else {
            this.theta = Math.atan(( getParteImaginaria()/getParteReal() ));}
            
            
        return this.theta ;
           
        
    }
    
    //Transformar Rectangular-Polar
    public String aPolar() {
       this.r = (Math.sqrt(Math.pow(getParteReal(), 2) + Math.pow(getParteImaginaria(), 2)));
        return this.r+"e^("+theta()+"i)";
    }
    
        

    //Transformar Polar-Rectangular
     public String aRectangular() {
//        double parteReal;
//        double parteImaginaria;
        this.parteReal=r*Math.cos(theta);
        this.parteImaginaria=r*Math.sin(theta);
        return this.parteReal+"+i "+this.parteImaginaria;
    }

    //Suma: (a,b)+(c,d)=(a+c,b+d)
    public String suma(Complejo otro) {
        System.out.println(this.getParteReal());
        System.out.println(otro.getParteReal());
        System.out.println((this.parteImaginaria));
         System.out.println((otro.getParteImaginaria()));
        
        
        this.parteReal = this.getParteReal() + otro.getParteReal();
        this.parteImaginaria += otro.getParteImaginaria();
        return (this.parteReal+" + i"+this.parteImaginaria);
    }

    //Resta: (a,b)-(c,d)=(a-c,b-d)
    public String resta(Complejo otro) {
        this.parteReal -= otro.getParteReal();
        this.parteImaginaria -= otro.getParteImaginaria();
        return (this.parteReal+" + i"+this.parteImaginaria);
    }

    //Producto: (a,b)*(c,d)=(ac-bd,ad+bc)
    public String producto(Complejo otro) {
        double parteReal = ((this.parteReal * otro.getParteReal()) - (this.parteImaginaria * otro.getParteImaginaria()));
        double parteImaginaria = ((this.parteReal * otro.parteImaginaria) + (this.parteImaginaria * otro.parteReal));

        this.parteReal = parteReal;
        this.parteImaginaria = parteImaginaria;
        return (this.parteReal+" +  i"+this.parteImaginaria);
    }

    //Dibision: (a,b)/(c,d)=((ac+bd)/(c2+d2),(bc-ad)/(c2+d2))
    public String division(Complejo otro) {
       String real="";
       String imaginario = "";
     
       int f=0,f2=0;
        if((this.parteReal * otro.parteReal) + (this.parteImaginaria * otro.parteImaginaria)%((Math.pow(otro.parteReal, 2) + (Math.pow(otro.parteImaginaria, 2))))==0){
         double parteReal = (((this.parteReal * otro.parteReal) + (this.parteImaginaria * otro.parteImaginaria))
                / ((Math.pow(otro.parteReal, 2) + (Math.pow(otro.parteImaginaria, 2))))); 
         this.parteReal=parteReal;
         f=1;
        }else{
            real= ((" ("+((this.parteReal * otro.parteReal) + (this.parteImaginaria * otro.parteImaginaria))+")" +" / "+"("+((Math.pow(otro.parteReal, 2) + (Math.pow(otro.parteImaginaria, 2))))+")" ));
          
        }
        
        if(((this.parteImaginaria * otro.parteReal) - (this.parteReal * otro.parteImaginaria))%((Math.pow(otro.parteReal, 2) + (Math.pow(otro.parteImaginaria, 2))))==0){
         double parteImaginaria = (((this.parteImaginaria * otro.parteReal) - (this.parteReal * otro.parteImaginaria))
                / ((Math.pow(otro.parteReal, 2) + (Math.pow(otro.parteImaginaria, 2)))));
         this.parteImaginaria=parteImaginaria;
         f2=1;
        }else{
            imaginario= ("("+((this.parteImaginaria * otro.parteReal) - (this.parteReal * otro.parteImaginaria))+")" +" i/ "+ "("+((Math.pow(otro.parteReal, 2) + (Math.pow(otro.parteImaginaria, 2))))+")");
            
        }
        
        if(f==1&&f2==1){   
            return (this.parteReal+" + "+this.parteImaginaria);
        }
        if(f==1&&f2!=1){
            return (this.parteReal+" + "+ imaginario);
        }
        if(f!=1&&f!=1){
           return (real+" + "+ imaginario);
        }
        if(f!=1&&f==1){
            return (real+" + "+ this.parteImaginaria);
        }
        return ("soy joto");
    }
    public Complejo conjugado(){
        return new Complejo(this.parteReal,-1*this.parteImaginaria);
    }
    public String aTrigonometrica() {
        this.r = (Math.sqrt(Math.pow(getParteReal(), 2) + Math.pow(getParteImaginaria(), 2)));
        theta();
        return this.r+"(Cos"+this.theta+")+ iSen("+this.theta+")";
    }
    public String[] euler(int raiz){
        String[] theta=new String[raiz];
        int f=0;
        this.r = (Math.sqrt(Math.pow(getParteReal(), 2) + Math.pow(getParteImaginaria(), 2)));
//        theta=String.valueOf(this.r)+"e^("+String.format("%.2f",theta())+2*pi)/"+raiz;
        for(int i=0;i<raiz;i++){
            if(theta()+(2*i*Math.PI)%raiz==0){
                theta[i]=String.valueOf(theta()+(2*i*Math.PI)/raiz);
            }else if(theta()+(2*i*Math.PI)%raiz!=0){
                theta[i]="("+String.format("%.2f",theta()+(2*i*Math.PI))+"/"+raiz+")i";
                f=1;
            }
        }
       
        return theta;
    }
    public String Euler2xd4k60fps(int n){
        String[] euler= euler(n);
        ArrayList<String> resultado = new ArrayList();
        this.r = (Math.sqrt(Math.pow(getParteReal(), 2) + Math.pow(getParteImaginaria(), 2)));
        for(int i=0;i<euler.length;i++){
            resultado.add("Raiz k="+i+",   "+String.format("%.2f", this.r)+"(e^"+euler[i]+")");
        }
        return resultado.toString();
    }
    public double[] raiz(int raiz){
        
        double[]raices =new double[raiz*2];
        this.r = (Math.sqrt(Math.pow(getParteReal(), 2) + Math.pow(getParteImaginaria(), 2)));
        this.theta= theta();
        if(this.theta<0){
            this.theta+=360;
        }
        
        for(int i=0,f=0;i<(raiz*2)-1;i+=2,f++){
           
            raices[i]=(Math.pow(r, (1/raiz)))*(Math.cos(this.theta+(2*f*Math.PI)/raiz));
            raices[i+1]=(Math.pow(r, (1/raiz)))*(Math.sin(this.theta+(2*f*Math.PI)/raiz));
            
        }
      return raices;
}
//    
//    @Override
//    public String toString() {
//        return "(" + parteReal + ","+"i "+
//                + parteImaginaria + ")";
//    }
//    
    public String raiz2xd4k60fps(int n){
        
        double[] raices = raiz(n);
        ArrayList<String> resultado = new ArrayList();
       
       
        System.out.println(resultado);
        for(int i=0;i<raices.length/2;i++){
           resultado.add(String.format("%.2f", new Double(raices[i*2]))+"+i"+String.format("%.2f", new Double(raices[(i*2)+1])));
        }
    return resultado.toString();}
    @Override
    public String toString() {
        return parteReal + "+i" + parteImaginaria ;
    }
    public String potencia(int n){
        double cos;
        double sin;
        double theta;
        double r;
        
        theta=theta();
       
       
        r=Math.pow((Math.sqrt(Math.pow(this.parteReal, 2)+Math.pow(this.parteImaginaria, 2))),n);
      
        
        cos=(Math.cos((n)*theta));        
        cos=cos*r;
        sin=(Math.sin((n)*theta));
        sin=sin*r;
        if(n%2==0){
           return (cos+"+i"+sin);
        }else if(n%2!=0){
            return (sin+"+i"+cos);
        }
        
        return (cos+"+i"+sin);
    }
    
    public Complejo Clone(){
        return new Complejo(this.parteReal, this.parteImaginaria);
    }
    public static void main(String[] args) {
        
//  Calculadora frame = new Calculadora();
//    frame.setDefaultCloseOperation(Calculadora.EXIT_ON_CLOSE);
//    frame.setBounds(300,300,510,600);
//    frame.setLayout(new BorderLayout());
//    plano = new Plano();
//
//    //Bordes del panel
//   frame.add(plano, BorderLayout.CENTER);
//    //frame.add(start, BorderLayout.SOUTH);
//    frame.setVisible(true);
//        
    }

}

