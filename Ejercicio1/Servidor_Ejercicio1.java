

import java.net.*; // paquete que contienen clases de red , todo lo necesario para comunicarme en red
import java.io.*; // paquete que contienen clases para E/S teclado y monitor
import java.lang.Math;

public class Servidor_Ejercicio1 {
    
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Uso desde consola:  <numero puerto>");

            System.exit(1);
        }
        
        String[] frases = {"Perro que ladra, su rama nunca endereza", "Solo avanzas mas rapido, pero acompanado llegas mas lejos", "Asi lo quiso la sagrada linea del tiempo", "Pasenle una naranja al semestre porque me la va a pelar", "Uno es dueno de lo que calla, pero esclavo de lo que dice"};
        int numeroPuerto = Integer.parseInt(args[0]);// convertimos el numero de puerto
        
        try (
            ServerSocket socketdelServidor =
                new ServerSocket(Integer.parseInt(args[0]));//escuchando peticiones
            Socket socketdelCliente = socketdelServidor.accept();// se acepta la peticion     
            PrintWriter escritor =
                new PrintWriter(socketdelCliente.getOutputStream(), true);                   
            BufferedReader lector = new BufferedReader(
                new InputStreamReader(socketdelCliente.getInputStream()));
        ) {
            String linealeida, lineaEscrita;
            int pos;
            while ((linealeida = lector.readLine()) != null) {
                pos = (int)(Math.random()*(frases.length));
                lineaEscrita = frases[pos];
                escritor.println(lineaEscrita);
            }
        } catch (IOException e) {
            System.out.println(" ocurrio una excepcion cuando intentamos escuchar "
                + numeroPuerto + " o esperando por una conexicon");
            System.out.println(e.getMessage());
        }
    }
}
