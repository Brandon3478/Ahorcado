
package Cliente;

import java.io.*;
import java.net.Socket;
import java.util.*;


public class AhorcadoCliente {
    public static void main(String[] args) throws IOException {
        
        final String IP = "192.168.100.221";
        final int PUERTO = 1234;
        
        try 
        {
            Socket socket = new Socket(IP, PUERTO);
        
            System.out.println("Esperando a que el servidor conteste");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"ISO-8859-1"));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"ISO-8859-1"));
            
            String palabraAdivinadaHastaElMomento = "";
            Scanner teclado = new Scanner(System.in);

            //while(true){
                System.out.println("Cual es tu nombre: ");
                String nombre = teclado.nextLine();
                
                System.out.println("Escoge el nivel de dificultad o escribe salir: \n1.Facil \n2.Intermedio \n3.Dificil");
                String opcion = teclado.nextLine();
                pw.println(opcion);
                pw.flush();
                if(opcion.compareToIgnoreCase("salir")==0){
                    br.close();
                    pw.close();
                    System.exit(0);
                }
                else{
                    String aleatorio = br.readLine();
                    palabraAdivinadaHastaElMomento = inicializarPalabraAdivinadaHastaElMomento(aleatorio);
                    String letra;
                    int intentos =5;
                    
                    long startTime = System.nanoTime();
                    while(!palabraAdivinadaHastaElMomento.equals(aleatorio)){

                            
                            System.out.println("Se ha adivinado hasta el momento: ");
                            System.out.println(palabraAdivinadaHastaElMomento);
                            System.out.println("Indique una nueva letra para adivinar la palabra secreta: ");
                            letra = teclado.nextLine();
                            
                            
                            if(aleatorio.indexOf(letra)!=-1){
                                palabraAdivinadaHastaElMomento = actualizarPalabraAdivinadaHastaElMomento(aleatorio, palabraAdivinadaHastaElMomento, letra.charAt(0));
                            }
                            else{
                                intentos--;
                                System.out.println("Intentos: " + intentos);
                                if(intentos==0){
                                    break;
                                }
                            }
                    }
                            long endTime = System.nanoTime();
                            long duracion = ((endTime - startTime)/1000000000);
                            pw.println(nombre);
                            pw.flush();
                            pw.println(intentos);
                            pw.flush();
                            pw.println(duracion);
                            pw.flush();
                            if(palabraAdivinadaHastaElMomento.equals(aleatorio)){
                                System.out.println("¡Felicidades!. Usted ha encontrado la palabra correcta en un tiempo de: " + duracion + " segundos");
                            }
                            else{
                                System.out.println("Lo siento. HA PERDIDO. Duracion: " + duracion + " segundos" );
                            }   
                } 
            pw.close();
            br.close(); 
            socket.close();
            System.exit(0);
            //}  
        } catch (Exception e) {
            e.printStackTrace();
    }
}
    
    static String inicializarPalabraAdivinadaHastaElMomento(String palabra){
        
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            if(palabra.charAt(i)==' '){
                resultado += " ";
            }else{
                resultado += "-";
            }
        }
        return resultado;
    }
    
    static String actualizarPalabraAdivinadaHastaElMomento(String palabra, String palabraAdivinadaHastaElMomento, char letra){
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            if(palabra.charAt(i) == letra || palabra.charAt(i)== palabraAdivinadaHastaElMomento.charAt(i)){
                    resultado += palabra.charAt(i);
            }else{
                resultado += "-";
            }
        }
        return resultado;
    }
}
