
package Servidor;

import java.io.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.*;
import java.util.Random;


public class AhorcadoServidor {
    public static void main(String[] args) {

        try {
            
            int pto = 1234;
            ServerSocket s = new ServerSocket(pto);
            System.out.println("Servidor iniciado en el puerto " + pto + " esperando cliente ...");
            
            String[] facil = {"casa", "mama", "oso", "papa", "lona", "foco", "gato"};
            String[] intermedio = {"xochimilco", "ferrocarril", "almohada", "television", "veracruz", "bailarina", "guitarra"};
            String[] dificil = {"erase una vez", "bailar con ella", "el carro rojo", "sube la escalera", "gracias a dios", "come la quesadilla", "el pulque bueno"};
            
            for(;;){
                Socket cl = s.accept();
            
                System.out.println("\tCliente conectado desde " + cl.getInetAddress() + ":" + cl.getPort());
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream(),"ISO-8859-1"));
                BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream(),"ISO-8859-1"));
            
                //Aqui empieza el trasiego de informacion
                
                String opcion = br.readLine();
                
                if(opcion.equals("1")){
                    int posicionAleatoria = (int) Math.floor(Math.random() * facil.length);
                    System.out.println("La palabra a descubrir es: ");
                    pw.println(facil[posicionAleatoria]);
                    pw.flush();
                    System.out.println(facil[posicionAleatoria]);
                    
                }
                else if(opcion.equals("2")){
                    int posicionAleatoria = (int) Math.floor(Math.random() * intermedio.length);
                    System.out.println("La palabra a descubrir es: ");
                    pw.println(intermedio[posicionAleatoria]);
                    pw.flush();
                    System.out.println(intermedio[posicionAleatoria]);
                }
                else if(opcion.equals("3")){
                    int posicionAleatoria = (int) Math.floor(Math.random() * dificil.length);
                    System.out.println("La palabra a descubrir es: ");
                    pw.println(dificil[posicionAleatoria]);
                    pw.flush();
                    System.out.println(dificil[posicionAleatoria]);
                }
                else if(opcion.compareToIgnoreCase("salir")==0){
                    System.out.println("El cliente cerro la conexion ...");
                    br.close();
                    pw.close();
                    cl.close();
                    System.exit(0);
                }
                else{
                    System.out.println("Opcion no valida");
                    break;
                }
                String nombre = br.readLine();
                pw.println(nombre);
                int intentos = Integer.parseInt(br.readLine());
                int duracion = Integer.parseInt(br.readLine());
                System.out.println("Al jugador " + nombre + " le quedaron " + intentos + " intentos, en una duracion de " + duracion + " segundos, dando un puntaje de: " + (300-duracion));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
